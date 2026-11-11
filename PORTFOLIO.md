# Mandarin Inn Inventory Management System

> **A Java desktop inventory application for organizing hotel supplies by category and room.**

**Repository:** [eman-110/InventoryManagmentSystem](https://github.com/eman-110/InventoryManagmentSystem)
**Project type:** Desktop application · **Language:** Java · **Database:** MySQL

## Project Overview

The Mandarin Inn Inventory Management System is a desktop application for recording and reviewing hotel inventory. Its Swing interface presents dedicated screens for furniture, electronics, miscellaneous supplies, cleaning supplies, kitchen items, and laundry. Room-based categories associate stock quantities with rooms, while the kitchen-items screen maintains quantities at the category level. A separate room lookup brings together furniture, electronics, and miscellaneous inventory for a selected room.

The application connects its Java UI to MySQL using JDBC. The repository contains Java source, IntelliJ IDEA project files, MySQL Connector/J, screen image assets, and compiled `.class` files. It does not include a database schema, seed data, automated tests, or a Maven/Gradle build definition.

## Problem Solved

Hotel supplies can be difficult to track when item types, quantities, and room assignments are recorded separately. This project provides a single desktop interface for maintaining inventory by category and, for selected categories, reviewing which quantities are assigned to a room. It is designed to make routine stock updates and lookups more structured than ad hoc manual tracking.

## Target Users

- Hotel staff responsible for adding, removing, or checking supplies.
- Managers who need to review room-level inventory across supported categories.

These are intended users inferred from the application's hotel inventory workflows; the repository does not document a formal deployment or user study.

## Detailed Project Description

The application starts with a login window and, after a successful database-backed credential check, opens an image-led category menu. Each category screen provides controls to add item types and update or query stock. Furniture, electronics, miscellaneous supplies, cleaning supplies, and laundry are linked to rooms through dedicated relationship tables. Kitchen items instead use a single quantity stored with each item record.

The Room screen loads room names from MySQL and displays a combined list of furniture, electronics, and miscellaneous items for a selected room. Category screens also provide search and total-data views backed by database queries and Swing tables. The interface is built with Swing layouts, custom rounded controls, category illustrations, and a consistent dark-green and warm-neutral palette.

## Architecture and Workflow

```text
Main
 └── MySQLConnection ── JDBC ── MySQL database
       └── Login
            └── MainPage
                 ├── Furniture
                 ├── Electronics
                 ├── Miscellaneous
                 ├── Clean
                 ├── KitchenItems
                 ├── Laundry
                 └── Room
```

1. `Main` schedules startup on Swing's event-dispatch thread and obtains a connection from `MySQLConnection`.
2. The connection is passed into `Login`. The login screen checks the entered username and password against the `login` table using a JDBC prepared statement.
3. On a matching record, the application opens `MainPage`, whose category buttons open the corresponding inventory screen.
4. Category screens load item and room options from MySQL and run prepared SQL statements for updates, searches, and result lists.
5. Search and total-data results are presented in Swing tables. The Room screen combines three category queries with `UNION ALL` to show selected room contents.
6. Category screens provide a Back action to return to the main menu.

The source is in the Java default package. UI screens receive a shared `Connection` instance rather than establishing their own connection. `DatabaseConnection` is also present as a second connection helper, but the application startup path uses `MySQLConnection`.

## Key Features

- **Database-backed login:** Checks supplied credentials against the `login` table.
- **Six inventory categories:** Furniture, electronics, miscellaneous, cleaning supplies, kitchen items, and laundry.
- **Quantity management:** Adds and removes stock through category-specific forms.
- **Room assignment:** Tracks furniture, electronics, miscellaneous items, cleaning supplies, and laundry by room.
- **Room inventory view:** Combines furniture, electronics, and miscellaneous item quantities for a selected room.
- **Inventory search and tables:** Category screens query stock and display matching or total records in Swing tables.
- **Visual desktop interface:** Uses category images, a branded login, custom rounded buttons/panels, and Swing forms.

## Technical Implementation

| Area | Implementation |
| --- | --- |
| Language | Java; source files use Swing/AWT and JDBC APIs |
| Desktop UI | Java Swing windows, layouts, combo boxes, tables, dialogs, and custom-painted controls |
| Persistence | MySQL accessed with JDBC `Connection`, `PreparedStatement`, and `ResultSet` |
| JDBC driver | Bundled MySQL Connector/J 9.4.0 JAR |
| Navigation | Main-menu event handlers open category screens and pass the current connection |
| Query patterns | Parameterized lookups and updates, joins for room/category data, and `UNION ALL` for a combined room view |
| IDE metadata | IntelliJ IDEA module and artifact configuration |
| Build setup | No Maven/Gradle build file or automated test suite is included |

## Database Design

The SQL queries in the source imply the following relational model. No `CREATE TABLE` statements or schema export are included, so these are the fields referenced by application code, not a complete or verified DDL specification.

| Table | Fields referenced |
| --- | --- |
| `login` | `username`, `password` |
| `Rooms` | `room_id`, `room_name` |
| `Furniture` | `furniture_id`, `furniture_name` |
| `Electronics` | `electronics_id`, `electronics_name` |
| `Miscellaneous` | `Miscellaneous_id`, `Miscellaneous_name` |
| `Clean` | `clean_id`, `clean_name` |
| `Laundry` | `Laundry_id`, `Laundry_name` |
| `FurnitureInRooms`, `ElectronicsInRooms`, `MiscellaneousInRooms`, `CleanInRooms`, `LaundryInRooms` | Category ID, `room_id`, `quantity` |
| `KitchenItems` | `KitchenItems_name`, `quantity` |

The room relationship tables connect a category record to a room and store its quantity. The kitchen-items screen instead reads and updates a quantity on `KitchenItems`. The application expects a MySQL database named `inventory` on `localhost:3306` by default; connection values are embedded in `MySQLConnection.java`. Before running the app, a developer must create a compatible schema and seed at least one login record.

## My Role and Contributions

I developed the Java desktop application and its core inventory workflows. My contributions include:

- Building the Swing login, main-menu, category-management, and room-inventory screens.
- Implementing the UI navigation between the menu and seven inventory destinations.
- Connecting UI actions to MySQL with JDBC for login checks, item creation, stock updates, searches, and result display.
- Modeling room-assigned stock through category-specific relationship tables and implementing a combined room lookup for three item categories.
- Styling the interface with custom rounded Swing components and bundled image assets.
- Configuring the IntelliJ project and bundling the MySQL JDBC driver.

The repository history attributes the recorded project commits to `eman-110`; the contribution summary above describes functionality present in the repository.

## Actual Technical Challenges and Solutions

- **Keeping inventory separated by category while assigning stock to rooms:** The implementation uses category-specific item tables and corresponding `*InRooms` tables containing item IDs, room IDs, and quantities. This supports room-scoped stock operations without mixing item categories.
- **Making room-level data useful across categories:** `Room` joins room assignments to item names and combines furniture, electronics, and miscellaneous results with `UNION ALL` into a common item-and-quantity table.
- **Connecting multiple screens to the same database session:** The application obtains a JDBC connection at startup and passes it into login, menu, and category screens, keeping SQL access available throughout the navigation flow.
- **Rendering database results in a desktop UI:** Category search and total-data methods read JDBC result sets and populate `DefaultTableModel`/`JTable` views, with dialogs used for validation and database feedback.
- **Making the UI visually consistent with standard Swing controls:** The project includes custom-painted rounded buttons and panels, a shared color palette, and local category and brand images.

### Known Constraints in the Current Repository

- The database schema and seed records are not included, so a new developer must construct the database to match the SQL references.
- `KitchenItems` queries use inconsistent item-name spellings (`KitchenItems_name`, `KitchenItem_name`, and `Kitchenitems_name`), which can prevent some operations from working with a single schema.
- Database credentials are embedded in source, and login compares the supplied password directly with the stored `password` value. This is not suitable for production credentials.
- Startup prints a message when the database connection is unavailable, but the login action still expects a valid connection; this should not be treated as a supported offline mode.
- The project has no automated tests or standard dependency/build descriptor. IntelliJ metadata also refers to Connector/J 9.0.0, while the bundled JAR is 9.4.0.

## Skills Demonstrated

- Java desktop application development with Swing and AWT.
- Event-driven UI design and multi-window navigation.
- JDBC database integration and parameterized SQL.
- Relational data modeling for category and room inventory.
- SQL joins, subqueries, and `UNION ALL` result composition.
- Displaying database records with `JTable` and `DefaultTableModel`.
- Input feedback and exception handling in desktop workflows.
- IntelliJ IDEA project configuration and dependency setup.
- Technical documentation and transparent communication of implementation constraints.

## Future Enhancements

- Add versioned schema migrations and seed data so the application can be set up reproducibly.
- Externalize database configuration and use secure credential management.
- Hash passwords and adopt a safer authentication flow.
- Standardize the `KitchenItems` column names and add a regression test for each operation.
- Extract data-access logic from Swing screens into reusable repository/service classes.
- Add database constraints and transactions for quantity changes and category/room associations.
- Add a Maven or Gradle build, automated tests, and CI checks.
- Add validation for positive quantities and insufficient-stock removal across all inventory categories.
- Refresh views after updates and improve behavior when the database is unavailable.
- Capture real screenshots and document a verified example database setup.

## Portfolio Summary

The Mandarin Inn Inventory Management System is a Java Swing desktop application for managing hotel inventory by category and room. I built the login and image-led navigation screens, six category workflows, and a room lookup for furniture, electronics, and miscellaneous items. The application connects to MySQL through JDBC, uses prepared statements for database operations, and renders search and inventory results in Swing tables. Room-specific stock is represented through category-to-room relationship tables, while kitchen inventory is maintained separately. This project demonstrates practical desktop UI development, event-driven navigation, relational data modeling, and SQL integration. The repository includes IntelliJ project files and the JDBC driver; it does not include a database schema or automated tests, which are important next steps for reproducible setup and verification.

## Project Card Description

Mandarin Inn Inventory Management System is a Java Swing desktop application for organizing hotel supplies by category and room. It connects to MySQL through JDBC, supports inventory updates and lookups, and provides a visual room-level view of selected stock. Built with Java, Swing, MySQL, and MySQL Connector/J for operational teams.

## Relevant Technical Tags

`Java` · `Java Swing` · `AWT` · `MySQL` · `JDBC` · `MySQL Connector/J` · `Desktop Application` · `SQL` · `IntelliJ IDEA` · `Inventory Management` · `Relational Data Modeling`
