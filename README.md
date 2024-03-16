# This Too Shall Pass 

Веб-приложение, предназначенное для помощи людям в решении их психологических проблем.

## Состав команды 

- [Преснухин Дмитрий (Redegit)](https://github.com/Redegit) - Frontend
- [Калинин Павел (Strawlll)](https://github.com/Strawlll) - Backend
- [Журавлева Анастасия (anashadoof)](https://github.com/anashadoof) - Project Manager
- [Арясин Никита (NikitArias)](https://github.com/NikitArias) - Toster

## Календарынй план

| Этапы                                                                         | Дата начала | Дата завершения |
| :---------------------------------------------------------------------------- | :---------: | :-------------: |
| Определить потенциальных стейкхолдеров, выработать план взаимодействия с ними | 17.02.2024  |   02.03.2024    |
| Выбрать методологию работы с системой контроля версий                         | 02.03.2024  |   16.03.2024    |
| TBD                                                                           |     TBD     |       TBD       |

## База знаний (пока пусто)
- [Анализ предметной области](#)
- [Требования](#)
- [Правила работы над проектом](#)
- [Архитектурное решение](#)
- [БД](#)
- [Макет Приложения](#)

## Методология ведения контроля версий

Основываемся на GitFlow

### Положение по именам веток разработки:

Долгоживущие:
- `main` - ветка для продакшн версии
- `develop` ветка для разработки

Префиксы тематических веток:
- `feature/` - новые фичи
- `hotfix/` - фиксы
- `release/` - релизы

### Порядок работы
- Для каждой нвоой фичи создается отдельная ветка с префиксом `feature/` от ветки `develop`

- После завершения работы над фичей создается Pull Request для мержда этой ветки обратно в ветку `develop`. Code Review проводит Redegit

- Когда набирается достаточное количество новой функциональности для выпуска, подготавливается новая ветка релиза от `develop`.

- После завершения всех необходимых правок и тестирования в ветке релиза, она интегрируется обратно как в `develop`, так и в `main`.

- Если в процессе разработки или после выпуска обнаруживаются ошибки, исправления должны производиться в ветке `develop` и затем интегрироваться в ветку релиза и `main`. Ветки исправлений имеют префикс `hotfix/`
