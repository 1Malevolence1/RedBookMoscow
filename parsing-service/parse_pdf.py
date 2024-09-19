import os
import glob
import time
import json
from typing import Optional
from tqdm.auto import tqdm

# Для извлечения изображений из PDF
from PIL import Image
from pdf2image import convert_from_path
import pytesseract

from image_crop import get_images_from_pdf, _delete_unsuitable_images
from convert2dto import create_fields, get_one_png2json


def parse_one_pdf_to_json(pdf_path: str, view: str) -> list[dict[str, Optional[str]]]:
    json_to_sql = []
    
    txt_path = save_text_to_file(pdf_path, pdf_path.replace('/pdf', '/txt').replace('.pdf', '.txt'))
    cnt_entity = get_images_from_pdf(pdf_path=pdf_path)
    
    _delete_unsuitable_images(input_folder='./data/img', output_folder='./data/img')
    jsons_base = create_fields(txt_path=txt_path)
    
    
    print(len(jsons_base, cnt_entity))
    for i, json_base in enumerate(jsons_base):
        directory = "./data/img"
        file_pattern = os.path.join(directory, f"{i}_*.png")
        matching_files = glob.glob(file_pattern)
        
        images = {"images": []}
        for file in matching_files:
            images['images'].append(get_one_png2json(file))
        
        json_base.update(images)
        json_base.update({"view": view})
        
        json_to_sql.append(json_base)
    
    return json_to_sql


def extract_text_from_image(image: Image) -> str:
    """Извлекает текст из изображения с помощью Pytesseract."""
    return pytesseract.image_to_string(image, lang='rus+eng')


def save_text_to_file(input_file: str, output_file: str) -> None:
    images = convert_from_path(input_file)
    full_text = ""
    
    for i, image in tqdm(enumerate(images)):
        text = extract_text_from_image(image)
        full_text += text + '\n'

    """Сохраняет извлеченный текст в файл."""
    with open(output_file, 'w', encoding='utf-8') as f:
        f.write(full_text)
    
    return output_file


def main() -> None:
    pdf_path = './data/pdf/9)KKM-2022Razdel5Bespozvonochnie-4chasts426-484.pdf'
    txt_path = './data/txt/9_kkm_bespozvonochnie-4chast.txt'
    json_to_sql = []
    json.dumps(json_to_sql, indent=4, ensure_ascii=False)

    cnt_entity = get_images_from_pdf(pdf_path=pdf_path)
    
    _delete_unsuitable_images(input_folder='./data/img', output_folder='./data/img')
    jsons_base = create_fields(txt_path=txt_path)
    
    
    print(len(jsons_base), cnt_entity)
    for i, json_base in enumerate(jsons_base):
        directory = "./data/img"
        file_pattern = os.path.join(directory, f"{i}_*.png")
        matching_files = glob.glob(file_pattern)
        
        images = {"images": []}
        for file in matching_files:
            images['images'].append(get_one_png2json(file))
        
        json_base.update(images)
        json_base.update({"view": "Безпозвоночные"})
        
        json_to_sql.append(json_base)
        print(json_base)
    
    with open('./data/result.json', 'w') as file:
        json.dump(json_to_sql, file, indent=4, ensure_ascii=False)

if __name__ == "__main__":
    start_time = time.monotonic()
    main()
    print(f"\n\nWork time: {time.monotonic() - start_time}")