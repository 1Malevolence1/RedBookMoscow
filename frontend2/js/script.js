function previewImage(event) {
            const input = event.target;
            const preview = document.getElementById('image-preview');
            preview.innerHTML = ''; // Очистить предыдущие превью

            if (input.files && input.files[0]) {
                const reader = new FileReader();
                reader.onload = function (e) {
                    const img = document.createElement('img');
                    img.src = e.target.result;
                    img.style.maxWidth = '100%';
                    img.style.maxHeight = '100%';
                    preview.appendChild(img);
                };
                reader.readAsDataURL(input.files[0]);
            }
        }
  

        document.getElementById('views').addEventListener('change', function() {
            const selectedAnimal = this.options[this.selectedIndex].textContent;;  
            const items = document.querySelectorAll('#article-list li');  
        
            items.forEach(function(item) {
                const animalName = item.querySelector('p').textContent;  
        
               
                if (selectedAnimal === 'Все' || animalName === selectedAnimal) {
                    item.style.display = 'list-item';
                } else {
                    item.style.display = 'none';
                }
            });
        });

        document.getElementById('searchInput').addEventListener('input', function() {
            const searchText = this.value.toLowerCase();  // Получаем введенный текст и преобразуем его в нижний регистр
            const items = document.querySelectorAll('#article-list li');  // Получаем все элементы списка
        
            items.forEach(function(item) {
                const animalName = item.querySelector('h2').textContent.toLowerCase();  // Текст параграфа в нижнем регистре
        
                // Если текст параграфа содержит введенный текст, показываем элемент
                if (animalName.includes(searchText)) {
                    item.style.display = 'block';  // Показываем элемент
                } else {
                    item.style.display = 'none';  // Скрываем элемент
                }
            });
        });
        