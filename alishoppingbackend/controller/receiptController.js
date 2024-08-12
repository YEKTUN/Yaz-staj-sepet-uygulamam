const db=require('../db/db');

const getReceipt = (req, res) => {
    db.all('SELECT * FROM receipt', [], (err, rows) => {
        if (err) {
            console.error('Error fetching receipts:', err.message);
            res.status(500).json({ error: 'Internal Server Error' });
        } else {
            res.status(200).json({ receipts: rows });
        }
    });
}

const setReceipt = (req, res) => {
    const { receiptDate, receiptTotal } = req.body;
    db.run('INSERT INTO receipt (receiptDate, receiptTotal) VALUES (?, ?)', [receiptDate, receiptTotal], function(err) {
        if (err) {
            console.error('Error inserting receipt:', err.message);
            res.status(500).json({ error: 'Internal Server Error' });
        } else {
            res.status(201).json({ message: 'Receipt added', id: this.lastID });
        }
    }
)};
module.exports = { getReceipt,setReceipt }