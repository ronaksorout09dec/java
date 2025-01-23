CREATE TABLE categories (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL
);

CREATE TABLE expenses (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    amount REAL NOT NULL,
    category_id INTEGER NOT NULL,
    description TEXT,
    date TEXT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories (id)
);
