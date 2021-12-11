import React, {useCallback, useEffect, useState} from 'react';
import axios from "axios";
import ProductTable from "./ProductTable";
import {Button, Col, Container, Form, Modal, Row, Stack} from "react-bootstrap";
import CustomSpinner from "../CustomComponents/CustomSpinner";

const Product = () => {
    const [response, setResponse] = useState();
    const [error, setError] = useState();
    const [loading, setLoading] = useState(false);
    const [show, setShow] = useState(false);
    const[product,setProduct] = useState({
        name :'',
        code :"",
        costPrice : 0,
        sellingPrice :0,
        description :"",
        unitsInStock :0,
        lowStockThreshold: 0});

    useEffect(async () => {
        await axios.get("http://localhost:9000/inv/products")
            .then(response => setResponse(response.data.data))
            .catch(error => setError(error));
            setLoading(false);
    },[loading]);

    const handleSubmit=async () => {
        setLoading(true);
        await axios.post("http://localhost:9000/inv/product",product)
            .catch(error => setError(error))
            .finally(() => {
                setLoading(false);
            setShow(false);})
    };

    const handleChange= (e) =>{
        const{id,value} = e.target;
        setProduct(prevState => ({
            ...prevState,
            [id]: value
        }));
    };

    const handleClose = () => setShow(false);
    const handleAddProduct=()=> setShow(true);
    const handleUpdateCallBack =() => setLoading(true);

    const handleDeleteCallBack =async (product) => {
        setLoading(true);
        await axios.delete("http://localhost:9000/inv/product",  {params: {id: product.id}})
            .catch(error => setError(error))
            .finally(() => {
                setLoading(true)
            });
    };



    return (
        <>
            <Container fluid>
            <Stack>
                <h1>Product List</h1>
                <Row>
                    <Col />
                    <Col>
                        <Button variant="primary" onClick={handleAddProduct}>Add Product</Button>{' '}
                    </Col>
                </Row>

            </Stack>
        <ProductTable products ={response} callBackDelete={handleDeleteCallBack} handleUpdateCallBack={handleUpdateCallBack}/>

            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Add Product</Modal.Title>
                </Modal.Header>
                <Modal.Body>

                    <Form>
                        <Form.Group className="mb-3" controlId="name">
                            <Form.Label>Product Name</Form.Label>
                            <Form.Control type="text" placeholder="Enter Product Name" onChange={handleChange} />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="code">
                            <Form.Label>Product Code</Form.Label>
                            <Form.Control type="text" placeholder="Enter Code" onChange={handleChange} />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="costPrice">
                            <Form.Label>Cost Price</Form.Label>
                            <Form.Control type="number" placeholder="Enter Cost price" onChange={handleChange} />
                        </Form.Group>


                        <Form.Group className="mb-3" controlId="sellingPrice">
                            <Form.Label>Selling Price</Form.Label>
                            <Form.Control type="number" placeholder="Enter Selling price" onChange={handleChange} />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="unitsInStock">
                            <Form.Label>Stock</Form.Label>
                            <Form.Control type="number" placeholder="Enter current stock" onChange={handleChange} />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="lowStockThreshold">
                            <Form.Label>Low stock Threshold</Form.Label>
                            <Form.Control type="number" placeholder="Enter low stock threshold" onChange={handleChange} />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="desc">
                            <Form.Label>Description</Form.Label>
                            <Form.Control type="text" placeholder="Enter Description" onChange={handleChange} />
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
            </Container>
        </>
    );
};

export default Product;
