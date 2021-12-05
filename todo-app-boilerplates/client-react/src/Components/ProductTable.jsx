import React from 'react';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import BootstrapTable from 'react-bootstrap-table-next';
import {Button, Table} from "react-bootstrap";

const columns = [{
    dataField: 'id',
    text: 'Product ID'
}, {
    dataField: 'name',
    text: 'Product Name'
},
    {
        dataField: 'code',
        text: 'Code'
    },
    {
    dataField: 'costPrice',
    text: 'Cost Price'
},{
    dataField: 'sellingPrice',
    text: 'Selling Price'
},
    {
        dataField: 'unitsInStock',
        text: 'Current Stock'
    },
    {
        dataField: 'lowStockThreshold',
        text: 'Low Stock Threshold'
    }
];
const MyComponent = (props) => {
    if(!props.products ) return null;


    const handleDelete =(product)=>{
        product && props.callBackDelete(product);
    };

    const handleUpdate =(product)=>{
        product && props.callBackUpdate(product);
    };
    return (
        <>
            <Table responsive="sm">
                <thead>
                <tr>
                    <th>#Id</th>
                    <th>Name</th>
                    <th>Code</th>
                    <th>Cost Price</th>
                    <th>Selling Price</th>
                    <th>Current Stock</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                { props.products.map((product, i) => (
                    <tr>
                        <td>{product.id}</td>
                        <td>{product.name}</td>
                        <td>{product.code}</td>
                        <td>{product.costPrice}</td>
                        <td>{product.sellingPrice}</td>
                        <td>{product.unitsInStock}</td>
                        <td>
                            <Button variant="info" onClick={() =>handleUpdate(product)}>Update</Button>{' '}
                            <Button variant="danger" onClick={() =>handleDelete(product)}>Delete</Button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </Table>
</>
    );
};

export default MyComponent;
