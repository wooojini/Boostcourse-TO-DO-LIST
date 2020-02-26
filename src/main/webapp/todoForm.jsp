<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="./css/todo.css">
    <link rel="stylesheet" href="./css/todo-form.css">
</head>

<body>
    <header>
        <div class="todo-form__header container">
            <h1>할일 등록</h1>
        </div>
    </header>

    <main>
        <div class="todo-form__main container overflow-auto">
            <form action="./todos" method="post">
                <div class="input-area">
                    <span class="input__label">어떤일인가요?</span>
                    <input type="text" name="title" class="input__title" placeholder="swift 공부하기(24자까지)" maxlength="24" required="required"/>
                </div>

                <div class="input-area">
                    <span class="input__label">누가 할일인가요?</span>
                    <input type="text" name="name" class="input__name" placeholder="홍길동" required="required"/>
                </div>
 
                <div class="input-area">
                    <span class="input__label">우선순위를 선택하세요.</span>
                    <input type="radio" class="input__sequence" name="sequence" value="1" required="required"/> 1순위
                    <input type="radio" class="input__sequence" name="sequence" value="2" required="required"/> 2순위
                    <input type="radio" class="input__sequence" name="sequence" value="3" required="required"/> 3순위
                </div>

                <div class="btn-area overflow-auto">
                    <a href="./main/todos">
                        <button type="button" class="btn-back btn--white">
                            <pre>< 이전</pre>
                        </button>
                    </a>
                    <div class="input-btn-area">
                        <input type="submit" class="btn-submit btn--skyblue" value="제출" />
                        <input type="reset" class="btn-reset btn--skyblue" value="내용지우기">
                    </div>
                </div>
            </form>
        </div>
    </main>
</body>

</html>