import React from 'react';
import {Button, Table} from "react-bootstrap";
import {Link} from "react-router-dom";

const PurchaseOrderTable = (props) => {

    const handleUpdate = (order)=>{

    };


    if(!props.orders ) return null;
    return (
        <>
            <Table responsive="sm">
                <thead>
                <tr>
                    <th>#Id</th>
                    <th>Name</th>
                    <th>Order Date</th>
                    <th>Received Date</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                { props.orders.map((order, i) => (
                    <tr key={order.id}>
                        <td>{i+1}</td>
                        <td><Link to={`/purchaseOrderDetail?id=${order.id}`} >{order.name}</Link></td>
                        <td>{order.orderDate}</td>
                        <td>{order.receivedDate}</td>
                        <td>{order.status}</td>
                    </tr>
                ))}
                </tbody>
            </Table>
            </>
    );
};

export default PurchaseOrderTable;
