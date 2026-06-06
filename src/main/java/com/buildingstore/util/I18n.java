package com.buildingstore.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Словарь переводов интерфейса (русский / английский).
 *
 * Метод {@link #bundle(Lang)} возвращает карту «ключ → текст» для выбранного
 * языка; в JSP используется как {@code ${t['ключ']}}. Названия категорий и
 * единиц измерения добавлены с префиксами {@code cat.} и {@code unit.}, чтобы
 * их тоже можно было переводить из шаблонов.
 */
public final class I18n {

    private I18n() {
    }

    public static Map<String, String> bundle(Lang lang) {
        return lang == Lang.EN ? en() : ru();
    }

    /** Текст «N товаров» / «N products» с учётом языка. */
    public static String count(Lang lang, int n) {
        if (lang == Lang.EN) {
            return n + (n == 1 ? " product" : " products");
        }
        return Plural.ru(n, "товар", "товара", "товаров");
    }

    private static Map<String, String> ru() {
        Map<String, String> m = new LinkedHashMap<>();
        m.put("brand", "СтройМаркет");
        m.put("title.home", "СтройМаркет — материалы для стройки и ремонта");
        m.put("title.cart", "Корзина — СтройМаркет");
        m.put("meta.home", "СтройМаркет — материалы для строительства и ремонта: цемент, кирпич, утеплители, краски, инструменты, сантехника.");

        m.put("search.placeholder", "Поиск по каталогу");
        m.put("nav.catalog", "Каталог");
        m.put("nav.cart", "Корзина");
        m.put("cur.rub.title", "Российский рубль");
        m.put("cur.byn.title", "Белорусский рубль");
        m.put("lang.ru.title", "Русский язык");
        m.put("lang.en.title", "Английский язык");

        m.put("intro.title", "Материалы для стройки и ремонта");
        m.put("intro.text", "Цемент, кирпич, утеплители, краски, инструменты и сантехника. Со склада в Москве, с доставкой по городу.");

        m.put("side.categories", "Категории");
        m.put("side.all", "Все товары");
        m.put("head.all", "Все товары");
        m.put("head.search_results", "Результаты поиска");
        m.put("search.results_for", "Результаты по запросу");
        m.put("search.reset", "сбросить");

        m.put("sort.default", "Сортировка");
        m.put("sort.cheap", "Сначала дешевле");
        m.put("sort.expensive", "Сначала дороже");
        m.put("sort.name", "По названию");

        m.put("card.add", "В корзину");
        m.put("card.details", "Подробнее");
        m.put("common.per", "за");

        m.put("empty.title", "Ничего не найдено");
        m.put("empty.text", "Попробуйте изменить запрос или выбрать другую категорию.");
        m.put("empty.show_all", "Показать все товары");
        m.put("order.success", "Заказ оформлен. Спасибо");

        m.put("crumb.catalog", "Каталог");
        m.put("product.in_stock", "В наличии:");
        m.put("product.out_of_stock", "Нет в наличии");
        m.put("product.quantity", "Количество");
        m.put("product.back", "← Назад в каталог");
        m.put("specs.title", "Характеристики");
        m.put("specs.category", "Категория");
        m.put("specs.price", "Цена");
        m.put("specs.unit", "Единица измерения");
        m.put("specs.stock", "Остаток на складе");

        m.put("cart.title", "Корзина");
        m.put("cart.empty_title", "Корзина пуста");
        m.put("cart.empty_text", "Добавьте товары из каталога, чтобы оформить заказ.");
        m.put("cart.go_catalog", "Перейти в каталог");
        m.put("cart.continue", "← Продолжить покупки");
        m.put("cart.clear", "Очистить корзину");
        m.put("cart.total_title", "Итого");
        m.put("cart.items", "Товаров");
        m.put("cart.pcs", "шт.");
        m.put("cart.delivery", "Доставка");
        m.put("cart.free", "бесплатно");
        m.put("cart.to_pay", "К оплате");
        m.put("cart.remove", "Удалить");
        m.put("cart.decrease", "Уменьшить");
        m.put("cart.increase", "Увеличить");

        m.put("checkout.title", "Оформление заказа");
        m.put("checkout.name", "Имя");
        m.put("checkout.phone", "Телефон");
        m.put("checkout.address", "Адрес доставки");
        m.put("checkout.submit", "Оформить заказ");
        m.put("checkout.name_ph", "Иван Иванов");
        m.put("checkout.phone_ph", "+7 999 000-00-00");
        m.put("checkout.address_ph", "Город, улица, дом");

        m.put("flash.added", "Товар добавлен в корзину!");
        m.put("footer.contacts", "8 800 555-35-35 · Москва, ул. Строителей, 1");

        // Категории
        m.put("cat.cement", "Цемент и смеси");
        m.put("cat.brick", "Кирпич и блоки");
        m.put("cat.insulation", "Утеплители");
        m.put("cat.paint", "Краски и лаки");
        m.put("cat.tools", "Инструменты");
        m.put("cat.flooring", "Напольные покрытия");
        m.put("cat.roofing", "Кровля");
        m.put("cat.plumbing", "Сантехника");

        // Единицы измерения
        m.put("unit.мешок", "мешок");
        m.put("unit.шт", "шт");
        m.put("unit.лист", "лист");
        m.put("unit.упак", "упак");
        m.put("unit.рулон", "рулон");
        m.put("unit.ведро", "ведро");
        m.put("unit.банка", "банка");
        m.put("unit.п.м.", "п.м.");
        return m;
    }

    private static Map<String, String> en() {
        Map<String, String> m = new LinkedHashMap<>();
        m.put("brand", "StroyMarket");
        m.put("title.home", "StroyMarket — building and renovation materials");
        m.put("title.cart", "Cart — StroyMarket");
        m.put("meta.home", "StroyMarket — building and renovation materials: cement, bricks, insulation, paints, tools and plumbing.");

        m.put("search.placeholder", "Search the catalogue");
        m.put("nav.catalog", "Catalogue");
        m.put("nav.cart", "Cart");
        m.put("cur.rub.title", "Russian ruble");
        m.put("cur.byn.title", "Belarusian ruble");
        m.put("lang.ru.title", "Russian language");
        m.put("lang.en.title", "English language");

        m.put("intro.title", "Materials for building and renovation");
        m.put("intro.text", "Cement, bricks, insulation, paints, tools and plumbing. From our Moscow warehouse with city-wide delivery.");

        m.put("side.categories", "Categories");
        m.put("side.all", "All products");
        m.put("head.all", "All products");
        m.put("head.search_results", "Search results");
        m.put("search.results_for", "Results for");
        m.put("search.reset", "reset");

        m.put("sort.default", "Sort");
        m.put("sort.cheap", "Price: low to high");
        m.put("sort.expensive", "Price: high to low");
        m.put("sort.name", "By name");

        m.put("card.add", "Add to cart");
        m.put("card.details", "Details");
        m.put("common.per", "per");

        m.put("empty.title", "Nothing found");
        m.put("empty.text", "Try changing your query or selecting another category.");
        m.put("empty.show_all", "Show all products");
        m.put("order.success", "Order placed. Thank you");

        m.put("crumb.catalog", "Catalogue");
        m.put("product.in_stock", "In stock:");
        m.put("product.out_of_stock", "Out of stock");
        m.put("product.quantity", "Quantity");
        m.put("product.back", "← Back to catalogue");
        m.put("specs.title", "Specifications");
        m.put("specs.category", "Category");
        m.put("specs.price", "Price");
        m.put("specs.unit", "Unit");
        m.put("specs.stock", "In stock");

        m.put("cart.title", "Cart");
        m.put("cart.empty_title", "Your cart is empty");
        m.put("cart.empty_text", "Add products from the catalogue to place an order.");
        m.put("cart.go_catalog", "Go to catalogue");
        m.put("cart.continue", "← Continue shopping");
        m.put("cart.clear", "Clear cart");
        m.put("cart.total_title", "Summary");
        m.put("cart.items", "Items");
        m.put("cart.pcs", "pcs");
        m.put("cart.delivery", "Delivery");
        m.put("cart.free", "free");
        m.put("cart.to_pay", "Total");
        m.put("cart.remove", "Remove");
        m.put("cart.decrease", "Decrease");
        m.put("cart.increase", "Increase");

        m.put("checkout.title", "Checkout");
        m.put("checkout.name", "Name");
        m.put("checkout.phone", "Phone");
        m.put("checkout.address", "Delivery address");
        m.put("checkout.submit", "Place order");
        m.put("checkout.name_ph", "John Smith");
        m.put("checkout.phone_ph", "+1 555 000-0000");
        m.put("checkout.address_ph", "City, street, building");

        m.put("flash.added", "Product added to cart!");
        m.put("footer.contacts", "8 800 555-35-35 · Moscow, Stroiteley St., 1");

        // Categories
        m.put("cat.cement", "Cement & mixes");
        m.put("cat.brick", "Bricks & blocks");
        m.put("cat.insulation", "Insulation");
        m.put("cat.paint", "Paints & varnishes");
        m.put("cat.tools", "Tools");
        m.put("cat.flooring", "Flooring");
        m.put("cat.roofing", "Roofing");
        m.put("cat.plumbing", "Plumbing");

        // Units of measure
        m.put("unit.мешок", "bag");
        m.put("unit.шт", "pcs");
        m.put("unit.лист", "sheet");
        m.put("unit.упак", "pack");
        m.put("unit.рулон", "roll");
        m.put("unit.ведро", "bucket");
        m.put("unit.банка", "can");
        m.put("unit.п.м.", "lin. m");
        return m;
    }
}
