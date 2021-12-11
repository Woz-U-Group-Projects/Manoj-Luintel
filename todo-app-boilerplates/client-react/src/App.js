import logo from './logo.svg';
import './App.css';
import {Link, Route} from "react-router-dom";
import Product from "./Components/product/Product";
import PurchaseOrder from "./Components/purchaseOrder/PurchaseOrder";
import SalesOrder from "./Components/salesOrder/SalesOrder";
import React from "react";

function App() {
  return (
      <>
        <Route path="/product" component={Product}/>
        <Route path="/purchaseOrder" component={PurchaseOrder}/>
        <Route path="/salesOrder" component={SalesOrder}/>
      </>
  );
}

export default App;
