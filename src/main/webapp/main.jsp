<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="todos" scope="page" value="${requestScope.todos}"></c:set>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="../css/todo.css">
    <link rel="stylesheet" href="../css/todo-main.css">
    <script src="../js/main.js"></script>
</head>

<body>
	<header>
		<div class="todo-main__header container">
			<h1 class="todo-main__header__title">나의 해야할 일들</h1>
			<div class="overflow-auto">
				<a href="../todoForm"><button class="btn-add btn--skyblue">새로운 TODO 등록</button></a>
			</div>
		</div>
	</header>

	<main>
		<div class="todo-main__main container overflow-auto">
			<section>
				<div class="todo-cards-area">
					<div class="todo-cards js-todo-cards__todo">
						<div class="todo-cards__caption-area">
							<h2 class="todo-cards__caption">TODO</h2>
						</div>

						<c:forEach var="todo" items="${todos}">
							<c:if test="${todo.getType() == 'TODO'}">
								<div class="todo-cards__card">
									<h3>${todo.getTitle()}</h3>
									<p class="todo-cards__card__info js-todo-cards__card__info"
										data-id="${todo.getId()}" data-type="${todo.getType()}">
										등록날짜:${todo.getRegDate()}, ${todo.getName()},
										우선순위${todo.getSequence()}</p>
									<button class="btn-move js-btn-move">→</button>
								</div>
							</c:if>
						</c:forEach>
					</div>

					<div class="todo-cards js-todo-cards__doing">
						<div class="todo-cards__caption-area">
							<h2 class="todo-cards__caption">DOING</h2>
						</div>

						<c:forEach var="todo" items="${todos}">
							<c:if test="${todo.getType() == 'DOING'}">
								<div class="todo-cards__card">
									<h3>${todo.getTitle()}</h3>
									<p class="todo-cards__card__info js-todo-cards__card__info"
										data-id="${todo.getId()}" data-type="${todo.getType()}">
										등록날짜:${todo.getRegDate()}, ${todo.getName()},
										우선순위${todo.getSequence()}</p>
									<button class="btn-move js-btn-move">→</button>
								</div>
							</c:if>
						</c:forEach>
					</div>

					<div class="todo-cards js-todo-cards__done">
						<div class="todo-cards__caption-area">
							<h2 class="todo-cards__caption">DONE</h2>
						</div>

						<c:forEach var="todo" items="${todos}">
							<c:if test="${todo.getType() == 'DONE'}">
								<div class="todo-cards__card">
									<h3>${todo.getTitle()}</h3>
									<p class="todo-cards__card__info js-todo-cards__card__info">
										등록날짜:${todo.getRegDate()}, ${todo.getName()},
										우선순위${todo.getSequence()}</p>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</section>
		</div>
	</main>
</body>
</html>