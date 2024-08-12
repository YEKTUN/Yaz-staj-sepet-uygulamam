const router=require('express').Router();

const productController=require('../controller/productController');

router.get('/products',productController.getProduct);


module.exports=router