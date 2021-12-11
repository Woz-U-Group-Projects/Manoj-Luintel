import React, {useCallback, useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import axios from "axios";
import ProductTable from "./ProductTable";
import {Button, Form, Modal} from "react-bootstrap";
import CustomSpinner from "./ui/CustomSpinner";

const Product = (url, config) => {
    const [response, setResponse] = useState();
    const [error, setError] = useState();
    const [loading, setLoading] = useState(false);
    const [show, setShow] = useState(false);
    const [showUpdate, setShowUpdate] = useState(false);

    let product = {
        id: null,
        name :'',
        code :"",
        costPrice : 0,
        sellingPrice :0,
        description :"",
        unitsInStock :0,
        lowStockThreshold: 0,

    }

    useEffect(async () => {
        await axios.get("http://localhost:9000/inv/products")
            .then(response => setResponse(response.data.data))
            .catch(error => setError(error));
            setLoading(false);
    },[loading]);

    const handleAddProduct= ()=>{
            setShow(true);
    };

    const handleSubmit=async () => {
        setLoading(true);
        await axios.post("http://localhost:9000/inv/product",product)
            .catch(error => setError(error))
            .finally(() => {
                setLoading(false);
            setShow(false);})
    };

    const handleChangeName=(e) =>{
        product.name = e.target.value;
    };

    const handleChangeCode=(e) =>{
        product.code = e.target.value;
    };

    const handleChangeCp=(e) =>{
        product.costPrice = Number(e.target.value);

    };

    const handleChangeLowStock=(e) =>{
        product.lowStockThreshold = Number(e.target.value);


    };

    const handleChangeSp=(e) =>{
        product.sellingPrice = Number(e.target.value);

    };

    const handleChangeStock=(e) =>{
        product.unitsInStock = Number(e.target.value);

    };

    const handleChangeDescription=(e) =>{
        product.description = e.target.value;

    };

    const handleClose = () => setShow(false);

    const handleDeleteCallBack =async (product) => {
        setLoading(true);
        await axios.delete("http://localhost:9000/inv/product",  {params: {id: product.id}})
            .catch(error => setError(error))
            .finally(() => {
                setLoading(true)
            });
    };

    const handleUpdateCallBack =() => {
        setLoading(true);
    };

    return (
        <>
            <div>
                <h1>Product List</h1>
                <Button variant="primary" onClick={handleAddProduct}>Add Product</Button>{' '}
            </div>
        <ProductTable products ={response} callBackDelete={handleDeleteCallBack} handleUpdateCallBack={handleUpdateCallBack}/>

            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Add Product</Modal.Title>
                </Modal.Header>
                <Modal.Body>

                    <Form>
                        <Form.Group className="mb-3" controlId="name">
                            <Form.Label>Product Name</Form.Label>
                            <Form.Control type="text" placeholder="Enter Product Name" onChange={handleChangeName} />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="code">
                            <Form.Label>Product Code</Form.Label>
                            <Form.Control type="text" placeholder="Enter Code" onChange={handleChangeCode} />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="costPrice">
                            <Form.Label>Cost Price</Form.Label>
                            <Form.Control type="number" placeholder="Enter Cost price" onChange={handleChangeCp} />
                        </Form.Group>


                        <Form.Group className="mb-3" controlId="sellingPrice">
                            <Form.Label>Selling Price</Form.Label>
                            <Form.Control type="number" placeholder="Enter Selling price" onChange={handleChangeSp} />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="unitsInStock">
                            <Form.Label>Stock</Form.Label>
                            <Form.Control type="number" placeholder="Enter current stock" onChange={handleChangeStock} />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="lowStockThreshold">
                            <Form.Label>Low stock Threshold</Form.Label>
                            <Form.Control type="number" placeholder="Enter low stock threshold" onChange={handleChangeLowStock} />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="desc">
                            <Form.Label>Description</Form.Label>
                            <Form.Control type="text" placeholder="Enter Description" onChange={handleChangeDescription} />
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={handleSubmit}>
                        {loading?<CustomSpinner/>:"Submit"}
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    );
};

export default Product;
