import re
import base64
from typing import Any


def get_one_png2json(image_path: str, id_value: int = 0) -> dict[str, Any]:
    with open(image_path, 'rb') as file:
        image_data = file.read()
        encoded_image = base64.b64encode(image_data).decode('utf-8')

        image_dict = {
            "id": id_value,
            "name": image_path.split('/')[-1].replace('.png', ''),
            "originalFileName": "image.png",
            "size": len(image_data),
            "contentType": "image/png",
            "data": encoded_image
        }

        return image_dict


def create_fields(txt_path) -> list[dict[str, str]]:
    translations = {
        "Имя": "name",
        "Латинское имя": "latinName",
        "Отряд": "division",
        "Семейство": "family",
        "Статус": "status",
        "Распространение": "distribution",
        "Численность": "inHabitat",
        "Особенности обитания": "habitatFeatures",
        "Лимитирующие факторы": "mitigatingFactors",
        "Принятые меры охраны": "protectionMeasuresTaken",
        "Изменение состояния вида": "changesInStatusOfSpecies",
        "Необходимые мероприятия по сохранению вида": "neededConservationActions",
        "Необходимые мероприятия по восстановлению вида": "neededConservationActions",
        "Источники информации": "sourcesOfInformation",
        "Автор": "authors",
        "Авторы": "authors",
        "Фото": "image_sign"
    }
    
    results = []
    
    with open(txt_path, 'r', encoding='utf-8') as f:
        lines = f.readlines()
        
        current_key = None  # Хранит текущий ключ
        current_value = []   # Хранит текущее значение
        result = {}         # Хранит текущий результат
        
        for i, line in enumerate(lines):
            line = line.strip()

            if _is_english(line):
                results.append(result)
                if len(results) > 3:
                    return results
                if i >= 2:
                    result = {
                        "name": lines[i - 2].strip(),  # Предполагаем, что имя находится за две строки
                        "latinName": line,
                        "division": lines[i + 1].strip() if i + 1 < len(lines) else '',
                        "family": lines[i + 2].strip() if i + 2 < len(lines) else ''
                    }
                    continue

            found_key = False
            for key in translations.keys():
                if key in line:
                    found_key = True
                    if current_key is not None:
                        # Завершаем предыдущий ключ: объединяем все строки в одно значение
                        result[current_key] = ' '.join(current_value).strip().replace('\n', ' ')
                    # Устанавливаем новый текущий ключ
                    current_key = translations[key]
                    # Добавляем в значение всё, что идет после ключа
                    current_value = [line.strip()]
                    break    

            if not found_key:
                if current_key is not None:
                    current_value.append(line)

        if current_key is not None:
            result[current_key] = ' '.join(current_value).strip().replace('\n', ' ')

    return results


def _is_english(text: str) -> bool:
    pattern = r'^[A-Z][a-z]+(\s[a-z]+)?(\s\([A-Za-z\s,&]+[0-9]{4}\))?$'
    if re.fullmatch(pattern, text):
        return not text.isdigit() and len(text.split()) >= 2
    return False


def main() -> None:
    # path_run = './data/tmp/9_kkm_bespozvonochnie-4chast.txt'
    # path_debug = r'.\data\tmp\9_kkm_bespozvonochnie-4chast.txt'
    
    # try:
    #     create_fields(path_run)
    # except FileNotFoundError:
    #     create_fields(path_debug)
    pass


if __name__ == "__main__":
    main()