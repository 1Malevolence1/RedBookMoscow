import os
import re
import time
from tkinter.font import families
from typing import Optional
from tqdm.auto import tqdm
from pprint import pprint
from collections import defaultdict

# Для извлечения текста из PDF
import fitz
import PyPDF2
import pdfplumber

# Для проверки типов в pdf
from pdfminer.high_level import extract_pages, extract_text
from pdfminer.layout import LTTextContainer, LTChar, LTFigure, LTItemT

# Для извлечения изображений из PDF
from PIL import Image
from pdf2image import convert_from_path
import pytesseract

from deep_translator import GoogleTranslator


def images_from_pdf(pdf_path: str) -> None:
    pdfFileObj = open(pdf_path, 'rb')
    pdfReaded = PyPDF2.PdfReader(pdfFileObj)
    
    # Извлекаем страницы из PDF
    for page_num, page in tqdm(enumerate(extract_pages(pdf_path)), 'Pages', position=2):
        
        if page_num > 1:
            break
        
        pageObj = pdfReaded.pages[page_num]
        page_text, line_format = [], []
        page_content = []
        
        page_elements = [(element.y1, element) for element in page._objs]
        page_elements.sort(key=lambda a: a[0], reverse=True)
        
        # Итеративно обходим элементы, из которых состоит страница
        for i, component in enumerate(page_elements): # tqdm(enumerate(page_elements), 'Elements', position=3, leave=False):
            # Положение верхнего края элемента в PDF, Элемент структуры страницы
            pos, element = component[0], component[1] 

            # Проверяем, является ли элемент текстовым
            if isinstance(element, LTTextContainer):
                line_text, format_per_line = _text_extraction(element=element)
                # page_text.append(line_text)
                # line_format.append(format_per_line)
                # name_key = _get_name_key(line_text, format_per_line)
                # if name_key:
                    # print(name_key)
                page_text.append(line_text)



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


def extract_text_from_image(image):
    """Извлекает текст из изображения с помощью Pytesseract."""
    return pytesseract.image_to_string(image, lang='rus+eng')

def save_text_to_file(text, output_file):
    """Сохраняет извлеченный текст в файл."""
    with open(output_file, 'w', encoding='utf-8') as f:
        f.write(text)



def main() -> None:
    path1 = '.\\data\\pdf\\9)KKM-2022Razdel5Bespozvonochnie-4chasts426-484.pdf'
    path2 = r'C:\Users\whatt\Projects\WORK\RedBookMoscow\parsing-service\data\pdf\9)KKM-2022Razdel5Bespozvonochnie-4chasts426-484.pdf'
    # На windows ошибка с тем, что путь он не видит
    # try:
        # text_from_pdf(pdf_path=path1)
    # except FileNotFoundError:
        # text_from_pdf(pdf_path=path2)
    
    images = convert_from_path(path1)
    full_text = ""
    
    for i, image in enumerate(images):
        text = extract_text_from_image(image)
        full_text += text + '\n'

    save_text_to_file(full_text, './data/tmp/test_txt2.txt')


if __name__ == "__main__":
    start_time = time.monotonic()
    main()
    print(f"Work time: {time.monotonic() - start_time}")