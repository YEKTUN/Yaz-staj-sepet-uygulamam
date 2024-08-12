const sqlite3 = require('sqlite3').verbose();
const path = require('path');

const dbPath = path.join( __dirname, 'database.sqlite');
const db = new sqlite3.Database(dbPath, (err) => {
    if (err) {
        console.error('Error connecting to database:', err.message);
    } else {
        console.log('Database connected');
    }
});

// db.serialize(() => {
//     db.run('CREATE TABLE IF NOT EXISTS auth (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT,email TEXT,tel TEXT)', (err) => {
//         if (err) {
//             console.error('Error creating table:', err.message);
//         } else {
//             console.log('auth table created');
//         }
//     });
// });

// db.serialize(() => {
//     db.run('CREATE TABLE IF NOT EXISTS products (id INTEGER PRIMARY KEY AUTOINCREMENT, productName TEXT, productImage TEXT,productPrice TEXT)', (err) => {
//         if (err) {
//             console.error('Error creating table:', err.message);
//         } else {
//             console.log('Product table created');
//         }
//     });
// });


// db.serialize(() => {
//     db.run(`
//         CREATE TABLE IF NOT EXISTS receipt (
//             receiptNumber INTEGER PRIMARY KEY AUTOINCREMENT,
//             receiptDate TEXT,
//             receiptTotal INTEGER
//         )
//     `, (err) => {
//         if (err) {
//             console.error('Error creating receipts table:', err.message);
//         } else {
//             console.log('Receipt table created');
//         }
//     });
// });
// db.serialize(() => {
//     db.run(`
//         CREATE TABLE IF NOT EXISTS receiptDetail (
//             detailId INTEGER PRIMARY KEY AUTOINCREMENT,
//             productName TEXT,
//             quantity INTEGER,
//             total INTEGER,
//             price DOUBLE,
//             receiptNumber INTEGER,
//             FOREIGN KEY (receiptNumber) REFERENCES receipt (receiptNumber)
//         )
//     `, (err) => {
//         if (err) {
//             console.error('Error creating receiptDetails table:', err.message);
//         } else {
//             console.log('ReceiptDetail table created');
//         }
//     });
// });



module.exports = db;
