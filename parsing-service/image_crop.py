import os
import re
from PIL import Image
from pdf2image import convert_from_path
from pdfminer.high_level import extract_pages
from pdfminer.layout import LTFigure, LTTextContainer, LTChar
from tqdm import tqdm
import pytesseract
import PyPDF2



def get_images_from_pdf(pdf_path: str) -> None:
    pdfFileObj = open(pdf_path, 'rb')
    pdfReaded = PyPDF2.PdfReader(pdfFileObj)
    cnt_entity = 0
    prev_page_num = -1
    
    # Извлекаем страницы из PDF
    for page_num, page in tqdm(enumerate(extract_pages(pdf_path)), 'Pages', position=2):
        
        pageObj = pdfReaded.pages[page_num]
        page_elements = [(element.x1, element.y1, element) for element in page._objs]
        page_elements.sort(key=lambda a: (a[1], a[0]), reverse=False)
        
        # Итеративно обходим элементы, из которых состоит страница
        for i, component in enumerate(page_elements): # tqdm(enumerate(page_elements), 'Elements', position=3, leave=False):
            # Положение верхнего края элемента в PDF, Элемент структуры страницы
            _, _, element = component[0], component[1], component[2] 

            if isinstance(element, LTTextContainer):
                element_text = element.get_text()
                if _split_ru_en_string(element_text[:50]):
                    if page_num != prev_page_num:
                        cnt_entity += 1
                        prev_page_num = page_num
            
            if isinstance(element, LTFigure):
                # Вырезаем изображение из PDF
                cropped_image_path = _crop_image(element, pageObj)
                # Нужно делать проверку, если текст вида еще не закончен, то page_num для сохранения f'./data/img/{page_num - 1}_{i}_{i}'
                if os.path.exists(f'./data/img/{cnt_entity}_{i}'):
                    new_path = f"./data/img/{cnt_entity}_{hash(f'{cnt_entity}_{i}')}"
                else:
                    new_path = f"./data/img/{cnt_entity}_{i}"
                    
                cropped_image_path = _convert_to_images(cropped_image_path, path_output_with_name=new_path)
    pdfFileObj.close()
    return cnt_entity


def _split_ru_en_string(text: str) -> tuple[str, str] | None:
    try:
        match = re.search(r'[A-Za-z]', text)
        if match:
            return text[: match.start()], text[match.start(): ]
        else:
            return None
    except TypeError:
        return None


def _delete_unsuitable_images(input_folder: str, output_folder: str) -> None:
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


def _is_many_white(image_path: str) -> bool:
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


def _is_horizontal(image: Image, max_width_to_height_ratio=3) -> bool:
    """Проверяет, является ли изображение слишком горизонтальным."""
    try:
        width, height = image.size
        return width / height > max_width_to_height_ratio
    except Exception:
        return False


def _has_text(image: str) -> bool:
    try:
        return bool(pytesseract.image_to_string(image).strip())
    except Exception:
        return False


# Функция для вырезания элементов изображений из PDF
def _crop_image(element, pageObj, path_output_with_name: str ='./data/img/cropped_image') -> str:
    try:
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
    except Exception as e:
        print(f'Error while cropping image: {e}')
        return ''


# Функция для преобразования PDF в изображения
def _convert_to_images(input_file: str, path_output_with_name: str ='./data/img/PDF_image') -> str:
    poppler_path = r"C:\poppler-24.02.0\Library\bin"
    try:
        image = convert_from_path(input_file, poppler_path=poppler_path)[0]
        image = convert_from_path(input_file, poppler_path=poppler_path)[0]

        output_path = f"{path_output_with_name}.png"
        image.save(output_path, "PNG")
        os.remove('./data/img/cropped_image.pdf')
        return f"{output_path}.png"

    except Exception as e:
        print(f"Error while converting PDF to image: {e}")
        return ''