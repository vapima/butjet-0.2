<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <meta charset="utf-8">
  <title>Cool page site</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
  <body>
  Привет. Получаем тестовый доступ к тест аккаунту - <i>test@test.tt:test</i><br><br>
    <button id="btn">Пойдем в Телегу!</button><!-- Кнопка для старта AJAX-запроса -->
    <div id="output"></div><!-- Блок для вывода результатов -->
    <script>
    $(function(){
      var auth = btoa('test@test.tt:test');
      var output = $('#output'); // блок вывода информации
      $('#btn').on('click', function(){
        $.ajax({
          url: 'http://127.0.0.1:8081/butJet0.2/person/token?type=telegram', // путь к php-обработчику
              headers: {
                  "Authorization": "Basic " + auth
              },
          type: 'GET', // метод передачи данных
          dataType: 'json', // тип ожидаемых данных в ответе
          beforeSend: function(){ // Функция вызывается перед отправкой запроса
            output.text('Запрос отправлен. Ждите ответа.');
          },
          error: function(req, text, error){ // отслеживание ошибок во время выполнения ajax-запроса
            output.text('Хьюстон, У нас проблемы! ' + text + ' | ' + error);
          },
          complete: function(){ // функция вызывается по окончании запроса
            output.append('<p>Запрос полностью завершен!</p>');
          },
           success: function (data, status, jqXHR) {
                  //console.log(jqXHR.getAllResponseHeaders());
                  var authBase = jqXHR.getResponseHeader('token');
                  output.html('<br><a href=https://t.me/butjetbot?start='+authBase+'>Окей. Жми сюда!</a>');
              }
          //success: function(json){ // функция, которая будет вызвана в случае удачного завершения запроса к серверу
            // json - переменная, содержащая данные ответа от сервера. Обзывайте её как угодно ;)
            //output.text(json); // выводим на страницу данные, полученные с сервера
          //}
        });
      });
    });
    </script>
  </body>
</html>

