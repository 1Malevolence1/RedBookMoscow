def pymupdf_extract_text_from_columns(pdf_path):
    with fitz.open(pdf_path) as pdf:
        for page_num in range(len(pdf)):
            page = pdf.load_page(page_num)
            text_instances = page.get_text("dict")  # Извлечение текста в формате словаря
            
            # Словарь для хранения текста по колонкам
            columns = defaultdict(list)
            
            # Анализируем текстовые блоки
            for block in text_instances["blocks"]:
                if block["type"] == 0:  # Проверяем, что это текстовый блок
                    for line in block["lines"]:
                        for span in line["spans"]:
                            x0 = span["bbox"][0]  # Левая координата
                            text = span["text"]
                            
                            # Определяем номер колонки по координате x0
                            if x0 < page.rect.width / 3:
                                column_index = 0  # Левая колонка
                            elif x0 < 2 * page.rect.width / 3:
                                column_index = 1  # Центральная колонка
                            else:
                                column_index = 2  # Правая колонка
                            
                            columns[column_index].append(text)

            result = []
            # Выводим текст из каждой колонки в нужном порядке
            for col_index in sorted(columns.keys()):
                result.append(" ".join(columns[col_index]))
    return '\n'.join(result)


def extract_text_from_columns(pdf_path):
    with pdfplumber.open(pdf_path) as pdf:
        full_text = []
        
        for page in pdf.pages[: 3]:
            page_text = []
            # Извлечение текста с учетом координат колонок
            # Предположим, что у нас три колонки, расположенные слева направо
            left_column = page.within_bbox((0, 0, page.width / 3, page.height)).extract_text()
            center_column = page.within_bbox((page.width / 3, 0, 2 * page.width / 3, page.height)).extract_text()
            right_column = page.within_bbox((2 * page.width / 3, 0, page.width, page.height)).extract_text()
            
            # Добавляем текст в нужном порядке
            page_text.append(left_column)
            page_text.append(center_column)
            page_text.append(right_column)
            
            full_text.append(''.join(page_text))

        return "\n".join(full_text)


def create_txt_files() -> None:
    pass




###############
def _get_name_key(line_text, format_per_line):
    name_key = line_text[line_text.find('\x1f') + 1: line_text.find('.')] \
                                                            .strip() \
                                                            .replace("-", '') \
                                                            .replace('\n', ' ') \
                                                            .replace('\t', ' ')
    bold_check = any(["Bold" in str(format) for format in format_per_line]) 
    italic_check = not any(["Italic" in str(format) for format in format_per_line])
    upper_check = any([name.isupper() for name in name_key.split()])
    if upper_check and bold_check:
        name_key = _get_valid_name_key(name_key, line_text)
        name_key = name_key.strip()
        if name_key != "Автор" and name_key != 'Авторы':
            name_key = name_key.replace('Отряд', "| Отряд").replace("Семейство", "| Семейство")
            name_key = name_key.split('|')
            try:
                name, latin_name = _split_ru_en_string(name_key[0].strip())
                division = name_key[1]
                family = name_key[2]
                return '\n'.join([name.strip(), latin_name.strip(), division.strip(), family.strip()])
            except Exception as e:
                return name_key.strip(), 'exception'
            
        else:
            return name_key.strip()
    else:
        if bold_check and italic_check:
            name_key = _get_valid_name_key(name_key, line_text)
            return name_key.strip()
    return None


def _split_ru_en_string(text):
    match = re.search(r'[A-Za-z]', text)
    if match:
        return text[: match.start()], text[match.start(): ]


def _get_valid_name_key(name_key,line_text):
    
    translations = {
        "Имя": "Name",
        "Латинское имя": "Latin Name",
        "Отряд": "Division",
        "Семейство": "Family",
        "Статус": "Status",
        "Распространение": "Distribution",
        "Численность": "Inhabitat",
        "Особенности обитания": "Habitat Features",
        "Лимитирующие факторы": "Mitigating factors",
        "Принятые меры охраны": "Protection measures taken",
        "Изменение состояния вида": "Changes in the status of the species",
        "Необходимые мероприятия по сохранению вида": "Needed conservation actions",
        "Источники информации": "Sources of Information",
        "Автор": "Authors",
        "Авторы": "Authors"
    }
    if ':' in name_key:
        name_key = name_key[: name_key.find(':')]
    return name_key


def _text_extraction(element) -> None:
    line_text = element.get_text()
    line_formats = []

    for text_line in element:
        if isinstance(text_line, LTTextContainer):
            # Итеративно обходим каждый символ в строке текста
            for character in text_line:
                if isinstance(character, LTChar):
                    # Добавляем к символу название шрифта
                    line_formats.append(character.fontname)
                    # Добавляем к символу размер шрифта
                    line_formats.append(character.size)

    # Формат может на будущее пригодиться
    format_per_line = list(set(line_formats))    
    return (line_text, format_per_line)
#############################
