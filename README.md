# Mandarin Inn Inventory Management System

A Java desktop application for managing inventory at The Mandarin Inn.

---

## Overview

The Mandarin Inn Inventory Management System is a desktop inventory tool built with Java Swing and MySQL. Staff can review and maintain item quantities across inventory categories, associate room inventory with rooms, and search or view inventory totals.

## Key Features

- Login screen backed by the MySQL `login` table.
- Inventory screens for furniture, electronics, miscellaneous items, cleaning supplies, kitchen items, and laundry.
- Add and remove inventory quantities; room-based categories associate quantities with a selected room.
- Search and total-data views on the category screens.
- Room inventory lookup for furniture, electronics, and miscellaneous items.
- Local image assets and custom Swing styling.

## Technologies Used

| Area | Technology |
| --- | --- |
| Language | Java |
| Desktop UI | Java Swing and AWT |
| Database | MySQL |
| Database connectivity | MySQL Connector/J 9.4.0 |
| IDE project files | IntelliJ IDEA |

## Project Structure

```text
InventoryManagmentSystem/
├── .gitignore
├── MandarinInnInventory.iml
├── README.md
├── .idea/
│   ├── artifacts/
│   │   └── MandarinInnInventory_jar.xml
│   ├── libraries/
│   │   └── mysql_connector_j_9_0_0.xml
│   ├── misc.xml
│   ├── modules.xml
│   └── uiDesigner.xml
├── lib/
│   └── mysql-connector-j-9.4.0.jar
└── src/
    ├── Clean.java / Clean.form
    ├── DatabaseConnection.java
    ├── Electronics.java / Electronics.form
    ├── Furniture.java / Furniture.form
    ├── KitchenItems.java / KitchenItems.form
    ├── Laundry.java / Laundry.form
    ├── Login.java / Login.form
    ├── Main.java
    ├── MainPage.java / MainPage.form
    ├── Miscellaneous.java / Miscellaneous.form
    ├── MySQLConnection.java
    ├── Room.java / Room.form
    ├── RoundedButton.java
    └── Resource/
        ├── *.png
        └── META-INF/MANIFEST.MF
```

The `.class` files currently present alongside the Java sources are compiled output and are omitted from this source-oriented tree. The IntelliJ module file refers to Connector/J 9.0.0, while the bundled library is 9.4.0; use the bundled JAR when configuring the project.

## Prerequisites

- JDK 8 or later. The source was verified to compile with JDK 21.
- A running MySQL Server instance.
- Git, if cloning the repository.
- An IDE such as IntelliJ IDEA (optional; command-line instructions are also provided).

## Installation & Setup

1. Clone the repository and enter its folder:

   ```bash
   git clone https://github.com/eman-110/InventoryManagmentSystem.git
   cd InventoryManagmentSystem
   ```

2. Make sure a MySQL Server is running locally. The application currently connects to `localhost:3306` and the database named `inventory`.

3. Create the database and create/populate the tables described in [Database Configuration](#database-configuration). This repository does not include a schema, migration, or seed-data script.

4. Configure the database connection in `src/MySQLConnection.java` for your local MySQL host, database, username, and password. `Main.java` uses this connection class. Do not commit real credentials. `src/DatabaseConnection.java` is another connection helper with a separately embedded configuration, but it is not used by the application's startup path.

5. Compile and launch from the repository root in Windows PowerShell:

   ```powershell
   New-Item -ItemType Directory -Force .\out | Out-Null
   $javaFiles = Get-ChildItem .\src -Filter *.java | ForEach-Object { $_.FullName }
   javac -cp ".\lib\mysql-connector-j-9.4.0.jar" -d .\out $javaFiles
   java -cp ".\out;.\src;.\lib\mysql-connector-j-9.4.0.jar" Main
   ```

   The `src` directory is included at runtime so the application can load its images from `src/Resource`.

### IntelliJ IDEA

Open `MandarinInnInventory.iml` or the project folder, configure a JDK, and add `lib/mysql-connector-j-9.4.0.jar` as a project library. Set `Main` as the run configuration's main class and make sure `src` is available on the runtime classpath for image resources.

## Database Configuration

The application expects a MySQL database named `inventory`. Create it with:

```sql
CREATE DATABASE inventory;
```

The Java code references the following tables and fields:

| Table(s) | Fields referenced by the application |
| --- | --- |
| `login` | `username`, `password` |
| `Rooms` | `room_id`, `room_name` |
| `Furniture` | `furniture_id`, `furniture_name` |
| `Electronics` | `electronics_id`, `electronics_name` |
| `Miscellaneous` | `Miscellaneous_id`, `Miscellaneous_name` |
| `Clean` | `clean_id`, `clean_name` |
| `Laundry` | `Laundry_id`, `Laundry_name` |
| `FurnitureInRooms`, `ElectronicsInRooms`, `MiscellaneousInRooms`, `CleanInRooms`, `LaundryInRooms` | Matching category ID, `room_id`, `quantity` |
| `KitchenItems` | `KitchenItems_name`, `quantity` |

The category relationship tables associate an item's ID with a room ID and quantity. The exact table definitions and initial login record are not supplied; create them to match the field names used in the Java SQL queries before using the application.

**Kitchen-items schema note:** the code refers to the item-name column using inconsistent spellings (`KitchenItems_name`, `KitchenItem_name`, and `Kitchenitems_name`). Verify and reconcile these queries with your MySQL schema before relying on all kitchen-item operations.

The connection settings are currently embedded in Java source rather than read from environment variables or a configuration file. Use a local development account and avoid committing passwords. The login query compares the supplied password directly against the `login.password` value, so this implementation should not be used with real or sensitive credentials without first adding secure password handling.

## Application Usage

1. Start the application and sign in with a user record already present in the `login` table.
2. Choose a category from the main menu.
3. In room-based categories, select an item and room, then use **Add** or **Remove** to adjust the quantity assigned to that room. Add category item names using the screen's item-type controls.
4. Use the available **Search** and **Total Data** actions to find or review inventory.
5. Open **Room** to select a room and review the furniture, electronics, and miscellaneous inventory associated with it.
6. Use **Back** to return to the main menu.

Kitchen items are maintained in the `KitchenItems` table with an overall quantity rather than a room assignment.

## Screenshots

Add application screenshots to `docs/screenshots/` and replace these placeholders:

| Screen | Screenshot |
| --- | --- |
| Login | _Add `docs/screenshots/login.png`_ |
| Main menu | _Add `docs/screenshots/main-menu.png`_ |
| Inventory screen | _Add `docs/screenshots/inventory.png`_ |

## Future Improvements

- Add versioned SQL schema and seed scripts for reliable first-time setup.
- Move connection settings out of source code and use environment-based configuration.
- Store and verify password hashes instead of comparing plaintext passwords.
- Resolve the inconsistent KitchenItems column names.
- Add a standard build configuration and automated tests.
- Avoid committing generated `.class` files alongside source code.

## Author

**eman-110** · [GitHub Profile](https://github.com/eman-110)
