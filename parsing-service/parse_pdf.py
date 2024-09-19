import os
import re
import glob
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
from convert2dto import create_fields, get_one_png2json


def parse_one_pdf_to_json(pdf_path: str) -> list:
    json_to_sql = []
    path1 = '.\\data\\pdf\\9)KKM-2022Razdel5Bespozvonochnie-4chasts426-484.pdf'
    path2 = r'C:\Users\whatt\Projects\WORK\RedBookMoscow\parsing-service\data\pdf\9)KKM-2022Razdel5Bespozvonochnie-4chasts426-484.pdf'
    
    # save_text_to_file(path2, path2.replace('/pdf', '/txt').replace('.pdf', '.txt'))
    cnt_entity = get_images_from_pdf(pdf_path=pdf_path)
    cnt_entity = get_images_from_pdf(pdf_path=path2)
    
    _delete_unsuitable_images(input_folder='./data/tmp', output_folder='./data/tmp')
    jsons_base = create_fields(txt_path='./data/tmp/9_kkm_bespozvonochnie-4chast.txt')
    
    print(len(jsons_base, cnt_entity))
    for i, json_base in enumerate(jsons_base):
        directory = "/parsing-service/data/tmp"
        file_pattern = os.path.join(directory, f"{i}_*.png")
        matching_files = glob.glob(file_pattern)
        
        images = {"images": []}
        for file in matching_files:
            images['images'].append(get_one_png2json(file))
        
        json_base.update(images)
        json_to_sql.append(json_base)
    
    return json_to_sql

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
    json_to_sql = parse_one_pdf_to_json(r'C:\Users\whatt\Projects\WORK\RedBookMoscow\parsing-service\data\pdf\9)KKM-2022Razdel5Bespozvonochnie-4chasts426-484.pdf')



if __name__ == "__main__":
    start_time = time.monotonic()
    main()
    print(f"Work time: {time.monotonic() - start_time}")