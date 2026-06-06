<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="${langCode}">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <meta name="description" content="<c:out value='${product.description}'/>"/>
  <title><c:out value="${product.name}"/> — ${t['brand']}</title>
  <link rel="stylesheet" href="${ctx}/css/style.css"/>
</head>
<body>
<%@ include file="sprite.jspf" %>

<header class="nav">
  <div class="nav__inner">
    <a href="${ctx}/" class="brand"><img class="brand__logo" src="${ctx}/img/logo.svg" alt=""/>${t['brand']}</a>
    <form class="search" action="${ctx}/" method="get" role="search" autocomplete="off">
      <input type="text" name="search" id="search-input" placeholder="${t['search.placeholder']}" autocomplete="off"
             data-suggest="${ctx}/api/suggest" data-ctx="${ctx}"/>
      <div id="search-suggest" class="suggest"></div>
    </form>
    <nav class="nav__links">
      <div class="cur-switch">
        <a href="${ctx}/lang?l=ru&back=${pagePathEnc}" title="${t['lang.ru.title']}"
           class="cur-switch__btn ${langCode eq 'ru' ? 'cur-switch__btn--active' : ''}">RU</a>
        <a href="${ctx}/lang?l=en&back=${pagePathEnc}" title="${t['lang.en.title']}"
           class="cur-switch__btn ${langCode eq 'en' ? 'cur-switch__btn--active' : ''}">EN</a>
      </div>
      <div class="cur-switch">
        <a href="${ctx}/currency?c=RUB&back=${pagePathEnc}" title="${t['cur.rub.title']}"
           class="cur-switch__btn ${cur.code eq 'RUB' ? 'cur-switch__btn--active' : ''}">₽</a>
        <a href="${ctx}/currency?c=BYN&back=${pagePathEnc}" title="${t['cur.byn.title']}"
           class="cur-switch__btn ${cur.code eq 'BYN' ? 'cur-switch__btn--active' : ''}"><svg class="cur-sign"><use href="#cur-byn"></use></svg></a>
      </div>
      <a href="${ctx}/" class="nav__link">${t['nav.catalog']}</a>
      <a href="${ctx}/cart" class="nav__link">${t['nav.cart']}<c:if test="${cartCount > 0}"><span class="cart-count">${cartCount}</span></c:if></a>
    </nav>
  </div>
</header>

<div class="detail">
  <nav class="breadcrumb" aria-label="breadcrumb">
    <a href="${ctx}/">${t['crumb.catalog']}</a>
    <span class="sep">/</span>
    <a href="${ctx}/?category=${product.category.slug}">${t['cat.'.concat(product.category.slug)]}</a>
    <span class="sep">/</span>
    <c:out value="${product.name}"/>
  </nav>

  <div class="detail__grid">
    <div class="detail__media">
      <img class="detail__img ${productImage.contains('/photos/') ? 'detail__img--photo' : ''}"
           src="${ctx}${productImage}" alt="<c:out value='${product.name}'/>"/>
    </div>

    <div class="detail__info">
      <span class="tag">${t['cat.'.concat(product.category.slug)]}</span>
      <h1 class="detail__name"><c:out value="${product.name}"/></h1>
      <p class="detail__desc"><c:out value="${product.description}"/></p>
      <div class="detail__price"><t:price value="${product.price}"/><span>${t['common.per']} ${t['unit.'.concat(product.unit)]}</span></div>

      <c:choose>
        <c:when test="${product.inStock}">
          <span class="stock">${t['product.in_stock']} ${product.stock} ${t['unit.'.concat(product.unit)]}</span>
          <form action="${ctx}/cart/add/${product.id}" method="post" class="qty">
            <input type="hidden" name="redirect" value="${currentUrl}"/>
            <label for="qty">${t['product.quantity']}</label>
            <input type="number" name="quantity" id="qty" value="1" min="1" max="${product.stock}"/>
            <button type="submit" class="btn btn--primary">${t['card.add']}</button>
          </form>
        </c:when>
        <c:otherwise>
          <span class="stock stock--out">${t['product.out_of_stock']}</span>
        </c:otherwise>
      </c:choose>

      <a href="${ctx}/" class="btn btn--ghost" style="align-self:flex-start;">${t['product.back']}</a>
    </div>
  </div>

  <section class="specs">
    <h2>${t['specs.title']}</h2>
    <dl class="specs__table">
      <div class="specs__row"><dt>${t['specs.category']}</dt><dd>${t['cat.'.concat(product.category.slug)]}</dd></div>
      <div class="specs__row"><dt>${t['specs.price']}</dt><dd><t:price value="${product.price}"/> ${t['common.per']} ${t['unit.'.concat(product.unit)]}</dd></div>
      <div class="specs__row"><dt>${t['specs.unit']}</dt><dd>${t['unit.'.concat(product.unit)]}</dd></div>
      <div class="specs__row"><dt>${t['specs.stock']}</dt><dd>${product.stock} ${t['unit.'.concat(product.unit)]}</dd></div>
    </dl>
  </section>
</div>

<c:if test="${not empty message}">
  <div class="flash"><c:out value="${message}"/></div>
</c:if>

<footer class="footer">
  <div class="footer__inner">
    <span>© 2026 ${t['brand']}</span>
    <span>${t['footer.contacts']}</span>
  </div>
</footer>

<script>
  document.querySelectorAll('.flash').forEach(function (el) {
    setTimeout(function () { el.remove(); }, 3600);
  });
</script>
<script src="${ctx}/js/app.js"></script>
</body>
</html>
