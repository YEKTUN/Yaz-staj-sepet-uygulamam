const db=require('../db/db');



const getAuth = (req, res) => {
    db.all('SELECT * FROM auth', [], (err, rows) => {
        if (err) {
            console.error('Error fetching auth:', err.message);
            res.status(500).json({ error: 'Internal Server Error' });
        } else {
            res.status(200).json({ auths: rows });
        }
    });
}

const setAuth = (req, res) => {
    const { username, password,email,tel } = req.body;
    db.run('INSERT INTO auth (username, password,email,tel) VALUES (?, ?,?,?)', [username, password,email,tel], function(err) {
        if (err) {
            console.error('Error inserting auth:', err.message);
            res.status(500).json({ error: 'Internal Server Error' });
        } else {
            res.status(201).json({ message: 'auth info added', id: this.lastID });
        }
    }
)};

const searchUsernameAndPassword = (req, res) => {
    const { username, password } = req.body;
    db.all('SELECT * FROM auth WHERE username = ? AND password = ?', [username, password], (err, rows) => {
        if (err) {
            console.error('Error fetching auth:', err.message);
            res.status(500).json({ error: 'Internal Server Error' });
        } else {
            res.status(200).json({ auths: rows });
        }
    });
}
const searchUsername=(req,res)=>{
    const {username}=req.body;
    db.all('SELECT * FROM auth WHERE username = ?', [username], (err, rows) => {
        if (err) {
            console.error('Error fetching auth:', err.message); 
            res.status(500).json({ error: 'Internal Server Error' });
        } else {
            res.status(200).json({ auths: rows });
        }
    });
}
 
module.exports = { getAuth,setAuth,searchUsernameAndPassword,searchUsername }