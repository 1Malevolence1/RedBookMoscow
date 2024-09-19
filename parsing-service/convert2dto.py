import re
from pprint import pprint


# def convert2dto(txt_path):
#     result = {
#         "text": "",
#         "images": [
#                     "image": {
#                             "id": 1,
#                             "name": "example_image",
#                             "originalFileName": "image.png",
#                             "size": 2048,
#                             "contentType": "image/png",
#                             "data": "iVBORw0KGgoAAAANSUhEUgAAAAUA"
#                              }
#                     ],
#     "view": "view"
    
#     }
    
#     return result


# def _get_translation_by_partial_key(input_key: str, translations: dict) -> str:
#     pattern = re.compile(re.escape(input_key), re.IGNORECASE)

#     for key in translations:
#         if pattern.search(key):
#             return translations[key]

#     return None


def create_fields(txt_path):
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
        "Источники информации": "sourcesOfInformation",
        "Автор": "authors",
        "Авторы": "authors"
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
                print(line)
                # Если это латинское имя, и мы нашли достаточное количество строк выше
                if i >= 2:
                    result = {
                        "Имя": lines[i - 2].strip(),  # Предполагаем, что имя находится за две строки
                        "Латинское имя": line,
                        "Отряд": lines[i + 1].strip() if i + 1 < len(lines) else '',
                        "Семейство": lines[i + 2].strip() if i + 2 < len(lines) else ''
                    }

            for key in translations.keys():
                if key in line:
                    if current_key is not None:
                        result[current_key] = ' '.join(current_value).strip().replace('\n', ' ')
                        current_value = []

                    current_key = translations[key]
                    current_value = [line.replace(key, '').strip()]
                    break
            
            else:
                if current_key is not None:
                    current_value.append(line)
        
        if current_key is not None:
            result[current_key] = ' '.join(current_value).strip().replace('\n', ' ')

        results.append(result)

    pprint(results)


def _is_english(text):
    pattern = r'^[A-Z][a-z]+(\s[a-z]+)?(\s\([A-Za-z\s,&]+[0-9]{4}\))?$'
    if re.fullmatch(pattern, text):
        return not text.isdigit() and len(text.split()) >= 2
    return False

def main():
    create_fields('./data/tmp/9_kkm_bespozvonochnie-4chast.txt')


if __name__ == "__main__":
    main()