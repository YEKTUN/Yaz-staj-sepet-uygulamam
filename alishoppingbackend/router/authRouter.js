const router=require('express').Router();

const authController=require('../controller/authController.js');
router.get('/auth',authController.getAuth);
router.post('/register',authController.setAuth);
router.post('/login',authController.searchUsernameAndPassword);
router.post('/searchUsername',authController.searchUsername);

module.exports=router