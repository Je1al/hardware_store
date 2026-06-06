<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="${langCode}">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <meta name="description" content="${t['title.cart']}"/>
  <title>${t['title.cart']}</title>
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
      <a href="${ctx}/cart" class="nav__link nav__link--active">${t['nav.cart']}<c:if test="${cartCount > 0}"><span class="cart-count">${cartCount}</span></c:if></a>
    </nav>
  </div>
</header>

<main class="cart">
  <h1 class="cart__title">${t['cart.title']}</h1>

  <c:choose>
    <c:when test="${empty cartItems}">
      <div class="empty">
        <p class="empty__title">${t['cart.empty_title']}</p>
        <p>${t['cart.empty_text']}</p>
        <a href="${ctx}/" class="btn btn--primary">${t['cart.go_catalog']}</a>
      </div>
    </c:when>
    <c:otherwise>

      <div class="cart-list">
        <c:forEach var="item" items="${cartItems}">
          <div class="cart-item">
            <div>
              <a href="${ctx}/product/${item.product.id}" class="cart-item__name"><c:out value="${item.product.name}"/></a>
              <div class="cart-item__cat">${t['cat.'.concat(item.product.category.slug)]}</div>
            </div>

            <div class="stepper">
              <form action="${ctx}/cart/update/${item.product.id}" method="post">
                <input type="hidden" name="quantity" value="${item.quantity - 1}"/>
                <button type="submit" aria-label="${t['cart.decrease']}">−</button>
              </form>
              <form action="${ctx}/cart/update/${item.product.id}" method="post">
                <input type="number" name="quantity" value="${item.quantity}" min="1"
                       onchange="this.form.submit()" aria-label="${t['product.quantity']}"/>
              </form>
              <form action="${ctx}/cart/update/${item.product.id}" method="post">
                <input type="hidden" name="quantity" value="${item.quantity + 1}"/>
                <button type="submit" aria-label="${t['cart.increase']}">+</button>
              </form>
            </div>

            <div class="cart-item__sum"><t:price value="${item.subtotal}"/></div>

            <form action="${ctx}/cart/remove/${item.product.id}" method="post">
              <button type="submit" class="icon-btn" title="${t['cart.remove']}" aria-label="${t['cart.remove']}">×</button>
            </form>
          </div>
        </c:forEach>
      </div>

      <div class="cart-actions">
        <a href="${ctx}/" class="btn btn--ghost">${t['cart.continue']}</a>
        <form action="${ctx}/cart/clear" method="post">
          <button type="submit" class="btn btn--danger">${t['cart.clear']}</button>
        </form>
      </div>

      <div class="summary">
        <h2>${t['cart.total_title']}</h2>
        <div class="summary__row"><span>${t['cart.items']}</span><span>${cartCount} ${t['cart.pcs']}</span></div>
        <div class="summary__row"><span>${t['cart.delivery']}</span><span class="free">${t['cart.free']}</span></div>
        <div class="summary__total"><span>${t['cart.to_pay']}</span><span><t:price value="${cart.totalPrice}"/></span></div>

        <div class="checkout">
          <h3>${t['checkout.title']}</h3>
          <form action="${ctx}/cart/checkout" method="post">
            <div class="field">
              <label for="name">${t['checkout.name']}</label>
              <input type="text" name="name" id="name" placeholder="${t['checkout.name_ph']}" required/>
            </div>
            <div class="field">
              <label for="phone">${t['checkout.phone']}</label>
              <input type="tel" name="phone" id="phone" placeholder="${t['checkout.phone_ph']}" required/>
            </div>
            <div class="field">
              <label for="address">${t['checkout.address']}</label>
              <input type="text" name="address" id="address" placeholder="${t['checkout.address_ph']}" required/>
            </div>
            <button type="submit" class="btn btn--primary btn--block">${t['checkout.submit']}</button>
          </form>
        </div>
      </div>

    </c:otherwise>
  </c:choose>
</main>

<footer class="footer">
  <div class="footer__inner">
    <span>© 2026 ${t['brand']}</span>
    <span>${t['footer.contacts']}</span>
  </div>
</footer>

<script src="${ctx}/js/app.js"></script>
</body>
</html>
