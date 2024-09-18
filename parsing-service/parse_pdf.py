import imaplib
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

from image_crop import get_images_from_pdf, _delete_unsuitable_images




def extract_text_from_image(image):
    """Извлекает текст из изображения с помощью Pytesseract."""
    return pytesseract.image_to_string(image, lang='rus+eng')

def save_text_to_file(input_file, output_file):
    images = convert_from_path(input_file)
    full_text = ""
    
    for i, image in tqdm(enumerate(images)):
        text = extract_text_from_image(image)
        full_text += text + '\n'

    """Сохраняет извлеченный текст в файл."""
    with open(output_file, 'w', encoding='utf-8') as f:
        f.write(full_text)


def main() -> None:
    path1 = '.\\data\\pdf\\9)KKM-2022Razdel5Bespozvonochnie-4chasts426-484.pdf'
    path2 = r'C:\Users\whatt\Projects\WORK\RedBookMoscow\parsing-service\data\pdf\9)KKM-2022Razdel5Bespozvonochnie-4chasts426-484.pdf'
    # На windows ошибка с тем, что путь он не видит
    # try:
        # text_from_pdf(pdf_path=path1)
    # except FileNotFoundError:
        # text_from_pdf(pdf_path=path2)
    
    
    # save_text_to_file(path2, path2.replace('/pdf', '/txt').replace('.pdf', '.txt'))
    get_images_from_pdf(pdf_path=path2)
    _delete_unsuitable_images(input_folder='./data/tmp', output_folder='./data/tmp')

    


if __name__ == "__main__":
    start_time = time.monotonic()
    main()
    print(f"Work time: {time.monotonic() - start_time}")