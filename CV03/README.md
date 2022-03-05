# Ukázkové requesty

## GET - Path Variable

```
GET http://localhost:8080/user/greeting/user/abcd
```

```
HTTP/1.1 200 
Content-Type: text/html;charset=UTF-8
Content-Language: cs-CZ
Transfer-Encoding: chunked
Date: Sat, 05 Mar 2022 09:57:44 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html">
    <title>Greeting started</title>
</head>
<body>
<p>Hello user your password is: abcd!</p>
<p>Counter 2!</p>
</body>
</html>
```

## GET - Request Param
```
GET http://localhost:8080/user/welcome?username=user
```

```
HTTP/1.1 200 
Content-Type: text/html;charset=UTF-8
Content-Language: cs-CZ
Transfer-Encoding: chunked
Date: Sat, 05 Mar 2022 10:00:26 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html">
    <title>Greeting started</title>
</head>
<body>
<p>Hello user your password is: 1234!</p>
<p>Counter 1!</p>
</body>
</html>
```

## POST - Request Param
```
POST http://localhost:8080/user/welcome1?username=user
```

```
HTTP/1.1 200 
Content-Type: text/html;charset=UTF-8
Content-Language: cs-CZ
Transfer-Encoding: chunked
Date: Sat, 05 Mar 2022 10:02:10 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html">
    <title>Greeting started</title>
</head>
<body>
<p>Hello user your password is: 1234!</p>
<p>Counter 3!</p>
</body>
</html>
```

