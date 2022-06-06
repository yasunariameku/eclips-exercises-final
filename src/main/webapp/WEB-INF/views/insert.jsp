<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>

  <div class="header">
    <h1 class="site_logo"><a href="menu.html">商品管理システム</a></h1>
    <div class="user">
      <p class="user_name">${name}さん、こんにちは</p>
      <form class="logout_form" action="logout.html" method="get">
        <button class="logout_btn" type="submit">
          <img src="images/ドアアイコン.png">ログアウト</button>
      </form>
    </div>
  </div>

  <hr>
  
  <div class="insert">
    <div class="discription">
      <p>
        登録情報を入力してください（<span class="required"></span>は必須です）
      </p>
    </div>
  
    <div class="form_body">
    
      <c:if test="${not empty msg}"><p class="error">${msg}</p></c:if>
      
      <form:form action="insert" method="post" modelAttribute="insert">
        <fieldset class="label-130">
          <div>
            <label class="required">商品ID</label>
            <form:input path="product_id" type="text" name="productId" class="base-text" />
            <form:errors path="product_id" cssStyle="color: red"/>
          </div>
          <div>
            <label class="required">商品名</label>
            <form:input path="name" type="text" name="productName" class="base-text" />
            <form:errors path="name" cssStyle="color: red"/>
          </div>
          <div>
            <label class="required">単価</label>
            <form:input path="price" type="text" name="price" class="base-text" />
            <form:errors path="price" cssStyle="color: red"/>            
          </div>
          <div class="select_block">
            <label class="required">カテゴリ</label>
            <form:select path="category_id" name="roleId" class="base-text">
              <option value="1">筆記具</option>
              <option value="2">紙製品</option>
              <option value="3">事務消耗品</option>
              <option value="4">オフィス機器</option>
              <option value="5">雑貨</option>
            </form:select>
          </div>
          <div>
            <label>商品説明</label>
            <form:textarea path="description" name="description" class="base-text"></form:textarea>
          </div>
          <div>
            <label>画像</label>
            <input  type="file" name="file" />
            <!-- <span class="error">エラーメッセージ</span> -->
          </div>
        </fieldset>
        <div class="btns">
          <form:button type="button" onclick="openModal()" class="basic_btn">登録</form:button>
          <input type="button" onclick="location.href='Search?search='" value="戻る" class="cancel_btn">
        </div>
        <div id="modal">
          <p class="modal_message">登録しますか？</p>
          <div class="btns">
            <form:button type="submit" class="basic_btn">登録</form:button>
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