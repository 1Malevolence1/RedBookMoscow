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


def get_images_from_pdf(pdf_path: str) -> None:
    pdfFileObj = open(pdf_path, 'rb')
    pdfReaded = PyPDF2.PdfReader(pdfFileObj)
    
    # Извлекаем страницы из PDF
    for page_num, page in tqdm(enumerate(extract_pages(pdf_path)), 'Pages', position=2):
        
        if page_num > 0:
            break
        
        pageObj = pdfReaded.pages[page_num]
        
        page_elements = [(element.y1, element) for element in page._objs]
        page_elements.sort(key=lambda a: a[0], reverse=True)
        
        # Итеративно обходим элементы, из которых состоит страница
        for i, component in enumerate(page_elements): # tqdm(enumerate(page_elements), 'Elements', position=3, leave=False):
            # Положение верхнего края элемента в PDF, Элемент структуры страницы
            pos, element = component[0], component[1] 

            if isinstance(element, LTFigure):
                # Вырезаем изображение из PDF
                cropped_image_path = _crop_image(element, pageObj)
                image_path = _convert_to_images(cropped_image_path, path_output_with_name=f'./data/tmp/{page_num}_{i}')
                
                


def _delete_unsuitable_images(input_folder, output_folder):
    if not os.path.exists(output_folder):
        os.makedirs(output_folder)

    for filename in os.listdir(input_folder):
        if filename.endswith(('.png', '.jpg', '.jpeg')):
            image_path = os.path.join(input_folder, filename).replace('\\', '/')
            with Image.open(image_path) as img:
                if _has_text(img):
                    os.remove(image_path)
                elif _is_horizontal(img):
                    os.remove(image_path)
                elif _is_many_white(image_path):
                    os.remove(image_path)


def _is_many_white(image_path):
    img = Image.open(image_path)
    img = img.convert('RGB')

    width, height = img.size
    white_pixel_count = 0

    for x in range(width):
        for y in range(height):
            r, g, b = img.getpixel((x, y))
            if (r, g, b) == (255, 255, 255):
                white_pixel_count += 1

    return white_pixel_count / (width * height) > 0.8


def _is_horizontal(image, max_width_to_height_ratio=3):
    """Проверяет, является ли изображение слишком горизонтальным."""
    width, height = image.size
    return width / height > max_width_to_height_ratio


def _has_text(image):
    return bool(pytesseract.image_to_string(image).strip())


# Функция для вырезания элементов изображений из PDF
def _crop_image(element, pageObj, path_output_with_name: str ='./data/tmp/cropped_image'):
    # Получаем координаты для вырезания изображения из PDF
    [image_left, image_top, image_right, image_bottom] = [element.x0,element.y0,element.x1,element.y1] 
    # Обрезаем страницу по координатам (left, bottom, right, top)
    pageObj.mediabox.lower_left = (image_left, image_bottom)
    pageObj.mediabox.upper_right = (image_right, image_top)
    # Сохраняем обрезанную страницу в новый PDF
    cropped_pdf_writer = PyPDF2.PdfWriter()
    cropped_pdf_writer.add_page(pageObj)
    # Сохраняем обрезанный PDF в новый файл
    with open(f'{path_output_with_name}.pdf', 'wb') as cropped_pdf_file:
        cropped_pdf_writer.write(cropped_pdf_file)
    return f'{path_output_with_name}.pdf'


# Функция для преобразования PDF в изображения
def _convert_to_images(input_file: str, path_output_with_name: str ='./data/tmp/PDF_image'):
    poppler_path = r"C:\poppler-24.02.0\Library\bin"
    image = convert_from_path(input_file, poppler_path=poppler_path)[0]

    output_path = f"{path_output_with_name}.png"
    image.save(output_path, "PNG")
    os.remove('./data/tmp/cropped_image.pdf')
    return f"{output_path}.png"


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