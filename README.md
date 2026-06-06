# StroyMarket — Building Materials Online Store

A web application for an online hardware / building-materials store, built with
**plain Java Servlets and JSP** running on an **embedded Apache Tomcat** server.
No application server needs to be installed separately — the project starts as an
ordinary Java application.

The catalogue (8 categories, 32 products) is stored in an **embedded H2 database**
and accessed through JDBC.

---

## Features

- **Product catalogue** — 8 categories, 32 products with photos, prices and stock.
- **Category filtering** and a **smart search** — multi-word, case- and «ё/е»-
  insensitive, searching across name, category and description with relevance ranking.
- **Live search suggestions** — an autocomplete dropdown backed by a JSON endpoint.
- **Sorting** — by price (ascending / descending) and by name.
- **Shopping cart** — stored in the HTTP session: add, change quantity, remove, clear.
- **Checkout** — order form with validation (name, phone, address).
- **Currency switch** — Russian ruble (₽) and Belarusian ruble (Br) with a fixed rate.
- **Language switch** — Russian (default) and English interface.
- **Embedded H2 database** — data persists between restarts; schema and seed data
  are created automatically on first run.
- **Optimisation** — gzip compression, browser caching of static assets, lazy-loaded
  images.

---

## Tech stack

| Technology | Purpose |
|---|---|
| Java 17 | Implementation language |
| Jakarta Servlet | HTTP request handling (controllers) |
| JSP + JSTL | HTML rendering (view) |
| Apache Tomcat 11 (embedded) | Servlet container |
| H2 Database (embedded) | Data storage via JDBC |
| Apache Maven | Build and dependency management |
| HTML, CSS, JavaScript | Markup and the live search suggestions |

---

## Getting started

Requirements: **JDK 17+** (works up to JDK 24). Maven is optional — a Maven Wrapper
(`mvnw`) is included.

### Run from the command line

```bash
./mvnw compile exec:exec
```

### Run from an IDE

Run the `main` method of the class **`com.buildingstore.Launcher`**
(Run As → Java Application).

Then open: **http://localhost:8080**

On the first run the embedded database is created at `./data/buildingstore.mv.db`
and filled with the catalogue automatically.

---

## Routes

| Method | Path | Description |
|---|---|---|
| GET  | `/`                   | Catalogue (search, category filter, sorting) |
| GET  | `/product/{id}`       | Product page |
| GET  | `/cart`               | Shopping cart |
| POST | `/cart/add/{id}`      | Add a product to the cart |
| POST | `/cart/update/{id}`   | Change item quantity |
| POST | `/cart/remove/{id}`   | Remove an item |
| POST | `/cart/clear`         | Clear the cart |
| POST | `/cart/checkout`      | Place an order |
| GET  | `/?sort=price_asc`    | Sorting (`price_asc` / `price_desc` / `name`) |
| GET  | `/api/suggest?q=...`  | JSON suggestions for the live search |
| GET  | `/currency?c=BYN`     | Switch display currency |
| GET  | `/lang?l=en`          | Switch interface language |

---

## Project structure

```
hardware_store/
├── pom.xml                       — Maven build (WAR)
├── mvnw, mvnw.cmd, .mvn/         — Maven Wrapper
└── src/main/
    ├── java/com/buildingstore/
    │   ├── Launcher.java          — entry point: starts embedded Tomcat
    │   ├── db/Database.java       — embedded H2 connection and schema
    │   ├── data/Catalog.java      — catalogue queries (JDBC) and search
    │   ├── model/                 — Product, Category, CartItem, Cart
    │   ├── servlet/               — Home, Product, Cart, Currency, Lang, Suggest
    │   └── util/                  — Currency, Money, Lang, I18n, Plural, Images, WebUtils
    └── webapp/
        ├── css/style.css          — styles
        ├── js/app.js              — live search suggestions
        ├── img/                   — product images and logo
        └── WEB-INF/
            ├── views/             — index.jsp, product.jsp, cart.jsp, sprite.jspf
            └── tags/price.tag     — price output with the currency symbol
```

---

## Architecture

The application follows the **MVC** pattern. The embedded Tomcat passes each request
to a servlet (controller); the servlet queries the data layer (`Catalog` over H2 via
JDBC) and forwards to a JSP page (view) that renders the HTML response. The cart is
kept in the user's session; interface text is resolved through the `I18n` dictionary
depending on the selected language.

---

## Notes

- Product names and descriptions are kept in their original (Russian) form; the
  interface chrome, categories and units are translated when English is selected.
- Currency conversion uses a fixed demo rate (1 BYN = 30 RUB).
- The generated database (`data/`) and build output (`target/`) are git-ignored.
