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
