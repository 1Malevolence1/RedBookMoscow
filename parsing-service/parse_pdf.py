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
    pass


def create_txt_files() -> None:
    pass



def _crop_image() -> None:
    pass


def _text_extraction() -> None:
    pass


def main() -> None:
    pass


if __name__ == "__main__":
    start_time = time.monotonic()
    main()
    print(f"Work time: {time.monotonic() - start_time}")