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
        pageObj = pdfReaded.pages[page_num]
        page_text, line_format = [], []
        
        
        # Находим все элементы
        page_elements = [(element.y1, element) for element in page._objs]
        # Сортируем все элементы по порядку нахождения на странице
        page_elements.sort(key=lambda a: a[0], reverse=True)
        
        print(page_elements)


def create_txt_files() -> None:
    pass



def _crop_image() -> None:
    pass


def _text_extraction() -> None:
    pass


def main() -> None:
    text_from_pdf(pdf_path='.\\data\\pdf\\9)KKM-2022Razdel5Bespozvonochnie-4chasts426-484.pdf')


if __name__ == "__main__":
    start_time = time.monotonic()
    main()
    print(f"Work time: {time.monotonic() - start_time}")