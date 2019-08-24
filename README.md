# jr_shortener
Приложение представляет из себя аналог укорачивателя ссылок Google URL Shortener (https://goo.gl), но обладает расширенной функциональность и является консольным. Приложение сокращает не только ссылки, но и любые строки. Shortener - это класс, который может для любой строки вернуть некий уникальный идентификатор и наоборот, по ранее полученному идентификатору вернуть строку.  
Два дополнительных требования к Shortener:  
* для двух одинаковых строк должен возвращаться один и тот же идентификатор; 
* он должен поддерживать столько строк, сколько значений может принимать long, именно этот тип будет использоваться для идентификатора.  

Первое требование очень сильно влияет на производительность, т.к. при получении идентификатора для новой строки мы должны проверить не обрабатывалась ли эта строка ранее, чтобы вернуть старый идентификатор.
