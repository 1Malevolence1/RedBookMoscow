import os
from re import X
import time
from typing import Optional
from tqdm.auto import tqdm

# Для извлечения текста из PDF
import PyPDF2

# Для проверки типов в pdf
from pdfminer.high_level import extract_pages, extract_text
from pdfminer.layout import LTTextContainer, LTChar, LTFigure, LTItemT

# Для извлечения изображений из PDF
from PIL import Image
from pdf2image import convert_from_path


def text_from_pdf(pdf_path: str) -> None:
    pdfFileObj = open(pdf_path, 'rb')
    pdfReaded = PyPDF2.PdfReader(pdfFileObj)
    
    text_per_page = {}
    
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
        for i, component in tqdm(enumerate(page_elements), 'Elements', position=3, leave=False):
            # Положение верхнего края элемента в PDF, Элемент структуры страницы
            pos, element = component[0], component[1] 

            # Проверяем, является ли элемент текстовым
            if isinstance(element, LTTextContainer):
                line_text, format_per_line = _text_extraction(element=element)
                # page_text.append(line_text)
                # line_format.append(format_per_line)

                # Добавляем текст в словарь
                # text_per_page['Name'] = line_text
                # print(text_per_page)
                
                if '\t\x1f' in line_text:
                    name_key = line_text[line_text.find('\t\x1f') + 2: line_text.find('.')].strip()
                    text_per_page[name_key] = line_text[line_text.find('\t\x1f') + 3:]

            print(text_per_page)



def create_txt_files() -> None:
    pass



def _crop_image() -> None:
    pass


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


def main() -> None:
    text_from_pdf(pdf_path='.\\data\\pdf\\9)KKM-2022Razdel5Bespozvonochnie-4chasts426-484.pdf')


if __name__ == "__main__":
    start_time = time.monotonic()
    main()
    print(f"Work time: {time.monotonic() - start_time}")