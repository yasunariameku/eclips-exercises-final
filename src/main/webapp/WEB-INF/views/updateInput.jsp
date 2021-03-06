<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
  <div class="header">
    <h1 class="site_logo"><a href="menu.html">商品管理システム</a></h1>
    <div class="user">
      <p class="user_name">佐藤さん、こんにちは</p>
      <form class="logout_form" action="logout.html" method="get">
        <button class="logout_btn" type="submit">
          <img src="images/ドアアイコン.png">ログアウト</button>
      </form>
    </div>
  </div>

  <hr>

  <div class="insert">
    <div class="form_body">
    <span class="error"><c:if test="${not empty msg}"><p class="error">${msg}</p></c:if></span>
      <form:form action="update" method="post" modelAttribute="update">
      <form:input path="id" type="hidden" name="id" value="${result.getId()}" readonly="readonly" class="base-text" />
      
        <fieldset class="label-130">
          <div>
            <label>商品ID</label>
            <form:input path="product_id" type="text" name="product_id" value="${result.getProduct_id()}" class="base-text" />
            <form:errors path="product_id" cssStyle="color: red"/>            
          </div>
          <div>
            <label>商品名</label>
            <form:input path="name" type="text" name="name" value="${result.getName()}" class="base-text" />
            <form:errors path="name" cssStyle="color: red"/>  
          </div>
          <div>
            <label>単価</label>
            <form:input path="price" type="text" name="price" value="${result.getPrice()}" class="base-text" />
            <form:errors path="price" cssStyle="color: red"/>
          </div>
          <div>
            <label>カテゴリ</label> <form:select path="category_id" items="${categoryList}" itemValue="id" itemLabel="name"  class="base-text">
            </form:select>
              <!-- <option value="1">筆記具</option>
              <option value="2">オフィス機器</option>
              <option value="3">事務消耗品</option>
              <option value="4">紙製品</option>
              <option value="5">雑貨</option> -->
            
          </div>
          <div>
            <label>商品説明</label>
            <form:textarea path="description" name="description" class="base-text" value="${result.getDescription()}"></form:textarea>
          </div>
          <div>
            <label>画像</label>
            <input type="file" name="file">
            <span class="error">エラーメッセージ</span>
          </div>
        </fieldset>
          <div class="btns">
            <button type="button" onclick="openModal()" class="basic_btn">更新</button>
            <input type="button" onclick="location.href='menu'" value="メニューに戻る" class="cancel_btn">
          </div>
          <div id="modal">
            <p class="modal_message">更新しますか？</p>
            <div class="btns">
              <form:button type="submit" name="update" class="basic_btn">更新</form:button>
              <button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
            </div>
          </div>
      </form:form>
    </div>
  </div>
  <div id="fadeLayer"></div>
</body>
</html>
<script src="./js/commons.js"></script>