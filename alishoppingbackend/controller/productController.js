const db=require('../db/db');

const getProduct = (req, res) => {
    db.all('SELECT * FROM products', [], (err, rows) => {
        if (err) {
            console.error('Error fetching products:', err.message);
            res.status(500).json({ error: 'Internal Server Error' });
        } else {
            res.status(200).json({ products: rows });
        }
    });
}

// const setProduct = (req, res) => {
//     const { productName, productImage,productPrice } = req.body;

//     db.run('INSERT INTO products (productName,productImage, productPrice) VALUES (?, ?,?)', [productName, productImage,productPrice],  (err)=> {
//         if (err) {
//             console.error('Error inserting product:', err.message);
//             res.status(500).json({ error: 'Internal Server Error' });
//         } else {
//             res.status(201).json({ message: 'Product added', id: this.lastID });
//         }
//     });
// }
// const products=[
//     {
//         "productPrice": "57",
//         "productName": "Backpack, Fits 15 Laptops",
//         "productImage": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
//     },
//     {
//         "productPrice": "89",
//         "productName": "Slim Fit T-Shirts",
//         "productImage": "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg"
//     },
//     {
//         "productPrice": "105",
//         "productName": "Mens Cotton Jacket",
//         "productImage": "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg"
//     },
//     {
//         "productPrice": "55",
//         "productName": "Mens Casual Slim Fit",
//         "productImage": "https://fakestoreapi.com/img/71YXzeOuslL._AC_UY879_.jpg"
//     },
//     {
//         "productPrice": "80",
//         "productName": "Gold & Silver Dragon Station Chain",
//         "productImage": "https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg"
//     },
//     {
//         "productPrice": "215",
//         "productName": "Gold Petite Micropave",
//         "productImage": "https://fakestoreapi.com/img/61sbMiUnoGL._AC_UL640_QL65_ML3_.jpg"
//     },
//     {
//         "productPrice": "168",
//         "productName": "White Gold Plated Princess",
//         "productImage": "https://fakestoreapi.com/img/71YAIFU48IL._AC_UL640_QL65_ML3_.jpg"
//     },
//     {
//         "productPrice": "144",
//         "productName": "Stainless Steel Double",
//         "productImage": "https://fakestoreapi.com/img/51UDEzMJVpL._AC_UL640_QL65_ML3_.jpg"
//     },
//     {
//         "productPrice": "169",
//         "productName": "External Hard Drive - USB 3.0",
//         "productImage": "https://fakestoreapi.com/img/61IBBVJvSDL._AC_SY879_.jpg"
//     },
//     {
//         "productPrice": "216",
//         "productName": "Internal SSD - SATA III 6 Gb/s",
//         "productImage": "https://fakestoreapi.com/img/61U7T1koQqL._AC_SX679_.jpg"
//     },
//     {
//         "productPrice": "126",
//         "productName": "Boost SATA III 2.5",
//         "productImage": "https://fakestoreapi.com/img/71kWymZ+c+L._AC_SX679_.jpg"
//     },
//     {
//         "productPrice": "65",
//         "productName": "Portable External Hard Drive",
//         "productImage": "https://fakestoreapi.com/img/61mtL65D4cL._AC_SX679_.jpg"
//     },
//     {
//         "productPrice": "88",
//         "productName": "(1920 x 1080) IPS Ultra-Thin",
//         "productImage": "https://fakestoreapi.com/img/81QpkIctqPL._AC_SX679_.jpg"
//     },
//     {
//         "productPrice": "99",
//         "productName": "Super Ultrawide Screen QLED",
//         "productImage": "https://fakestoreapi.com/img/81Zt42ioCgL._AC_SX679_.jpg"
//     },
//     {
//         "productPrice": "32",
//         "productName": "Snowboard Jacket Winter Coats",
//         "productImage": "https://fakestoreapi.com/img/51Y5NI-I5jL._AC_UX679_.jpg"
//     },
//     {
//         "productPrice": "170",
//         "productName": "Leather Moto Biker Jacket",
//         "productImage": "https://fakestoreapi.com/img/81XH0e8fefL._AC_UY879_.jpg"
//     },
//     {
//         "productPrice": "200",
//         "productName": "Striped Climbing Raincoats",
//         "productImage": "https://fakestoreapi.com/img/71HblAHs5xL._AC_UY879_-2.jpg"
//     },
//     {
//         "productPrice": "100",
//         "productName": "Short Sleeve Boat Neck V",
//         "productImage": "https://fakestoreapi.com/img/71z3kpMAYsL._AC_UY879_.jpg"
//     },
//     {
//         "productPrice": "55",
//         "productName": "Women's Short Sleeve Moisture",
//         "productImage": "https://fakestoreapi.com/img/51eg55uWmdL._AC_UX679_.jpg"
//     },
//     {
//         "productPrice": "17",
//         "productName": "DANVOUY Womens T Shirt",
//         "productImage": "https://fakestoreapi.com/img/61pHAEJ4NML._AC_UX679_.jpg"
//     }
// ]


// const insertProduct = (product) => {
//     return new Promise((resolve, reject) => {
//         db.run('INSERT INTO products (productName, productImage, productPrice) VALUES (?, ?, ?)',
//             [product.productName, product.productImage, product.productPrice],
//             function(err) {
//                 if (err) {
//                     console.error('Error inserting product:', err.message);
//                     reject(err);
//                 } else {
//                     resolve(this.lastID);
//                 }
//             });
//     });
// };

// const insertProducts = async () => {
//     for (const product of products) {
//         try {
//             await insertProduct(product);
//             console.log('Product inserted:', product.productName);
//         } catch (error) {
//             console.error('Failed to insert product:', product.productName);
//         }
//     }
// };
// insertProducts();

module.exports={getProduct}
