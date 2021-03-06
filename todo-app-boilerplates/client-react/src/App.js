import logo from './logo.svg';
import './App.css';
import {Link, Route} from "react-router-dom";
import Product from "./Components/product/Product";
import PurchaseOrder from "./Components/purchaseOrder/PurchaseOrder";
import SalesOrder from "./Components/salesOrder/SalesOrder";
import React from "react";
import PurchaseOrderDetail from "./Components/purchaseOrder/PurchaseOrderDetail";
import SalesOrderDetail from "./Components/salesOrder/SalesOrderDetail";

function App() {
  return (
      <>
        <Route path="/product" component={Product}/>
        <Route path="/purchaseOrder" component={PurchaseOrder}/>
        <Route path="/salesOrder" component={SalesOrder}/>
        <Route path="/purchaseOrderDetail" component={PurchaseOrderDetail}/>
        <Route path="/salesOrderDetail" component={SalesOrderDetail}/>
      </>
  );
}

export default App;
