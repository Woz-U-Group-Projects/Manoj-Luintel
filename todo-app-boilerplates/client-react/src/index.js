import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import {
    BrowserRouter,
    Route, Switch
} from "react-router-dom";
import reportWebVitals from './reportWebVitals';
import Header from "./Components/commons/Header";
import 'bootstrap/dist/css/bootstrap.min.css';
import Product from "./Components/product/Product";
import PurchaseOrder from "./Components/purchaseOrder/PurchaseOrder";
import SalesOrder from "./Components/salesOrder/SalesOrder";

ReactDOM.render(
  <React.StrictMode>
      <BrowserRouter>
          <Header/>
          <Switch>
              <Route path="/" component={App}/>
          </Switch>
      </BrowserRouter>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
