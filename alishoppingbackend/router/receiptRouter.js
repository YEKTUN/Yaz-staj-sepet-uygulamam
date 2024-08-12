const router=require('express').Router();
const receiptDetailController=require('../controller/receiptController');

router.get('/receipt',receiptDetailController.getReceipt);
router.post('/receiptSet',receiptDetailController.setReceipt);


module.exports=router