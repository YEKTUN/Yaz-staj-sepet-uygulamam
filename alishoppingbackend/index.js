const express=require('express');
const cors=require('cors');
const app=express();

const productRouter=require('./router/productRouter');
const receiptDetailRouter=require('./router/receiptDetailRouter');
const receiptRouter=require('./router/receiptRouter');
const authRouter=require('./router/authRouter');

app.use(cors());
app.use(express.json());

app.use("/api",productRouter);
app.use("/api",receiptDetailRouter);
app.use("/api",receiptRouter);
app.use("/api",authRouter)
    


app.listen(3000,()=>{
    console.log('server is running on port 3000');
})
