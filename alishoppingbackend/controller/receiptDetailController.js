const db=require('../db/db');

const getReceiptDetail = (req, res) => {
    db.all('SELECT * FROM receiptDetail', [], (err, rows) => {
        if (err) {
            console.error('Error fetching receipts:', err.message);
            res.status(500).json({ error: 'Internal Server Error' });
        } else {
            res.status(200).json({ receiptDetail: rows });
        }
    });
}
const setReceiptDetail = (req, res) => {
    const { receiptNumber, productName, quantity, total, price } = req.body;
    db.run('INSERT INTO receiptDetail (receiptNumber, productName, quantity, total, price) VALUES (?, ?, ?, ?, ?)', [receiptNumber, productName, quantity, total, price],  (err)=> {
        if (err) {
            console.error('Error inserting receipt:', err.message);
            res.status(500).json({ error: 'Internal Server Error' });
        } else {
            res.status(201).json({ message: 'ReceiptDetail added', id: this.lastID });
        }
    }
)};
module.exports = { getReceiptDetail ,setReceiptDetail}