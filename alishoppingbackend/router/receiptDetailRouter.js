const router=require('express').Router();
const receiptDetailController=require('../controller/receiptDetailController');

router.get('/receiptDetail',receiptDetailController.getReceiptDetail);
router.post('/receiptDetailSet',receiptDetailController.setReceiptDetail);

module.exports=router