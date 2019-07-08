# JXMLParsing
##Лекарственные препараты
Лекарственные препараты имеют следующие характеристики.
Name – наименование препарата.
Pharm – фирма-производитель.
Group – группа препаратов к которым относится лекарство (антибиотики,
болеутоляющие, витамины и т.п.).
Analogs (может быть несколько) – содержит наименование аналога.
Versions – варианты исполнения (консистенция/вид: таблетки, капсулы, порошок, капли и
т.п.). Для каждого варианта исполнения может быть несколько производителей
лекарственных препаратов со следующими характеристиками:
Certificate – свидетельство о регистрации препарата (номер, даты выдачи/истечения
действия, регистрирующая организация);
Package – упаковка (тип упаковки, количество в упаковке, цена за упаковку);
Dosage – дозировка препарата, периодичность приема.
Корневой элемент назвать Medicins.
