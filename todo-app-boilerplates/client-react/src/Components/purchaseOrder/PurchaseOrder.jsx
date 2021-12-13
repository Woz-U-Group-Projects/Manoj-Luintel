import React, {useEffect, useState} from 'react';
import {Button, Col, Container, Form, Modal, Row, Stack} from "react-bootstrap";
import CustomSpinner from "../CustomComponents/CustomSpinner";
import axios from "axios";
import PurchaseOrderTable from "./PurchaseOrderTable";

const PurchaseOrder = () => {
    const [response, setResponse] = useState();
    const [error, setError] = useState();
    const [loading, setLoading] = useState(false);
    const [show, setShow] = useState(false);
    const[purchaseOrder,setPurchaseOrder] = useState({
        id:null,
        name :'',
        orderDate:'',
        receivedDate:''});

    const handleClose = () => setShow(false);
    const handleCreate = () =>{
        setShow(true);
    };

    useEffect(async () => {
        await axios.get("http://localhost:9000/inv/purchase-orders")
            .then(response => setResponse(response.data.data))
            .catch(error => setError(error));
        setLoading(false);
    },[loading]);

    const handleSubmit=async () => {
        setLoading(true);
        await axios.post("http://localhost:9000/inv/purchase-order",purchaseOrder)
            .then(response=> console.log(response))
            .catch(error => setError(error))
            .finally(() => {
                setLoading(false);
                setShow(false);})
    };

    const handleChange= (e) =>{
        const{id,value} = e.target;
        setPurchaseOrder(prevState => ({
            ...prevState,
            [id]: value
        }));
    };

    const handleCallBackDelete =async (order)=>{
        setLoading(true);
        await axios.delete("http://localhost:9000/inv/purchase-order",  {params: {id: order.id}})
            .catch(error => setError(error))
            .finally(() => {
                setLoading(true)
            });
    };


    return (
        <Container fluid>
            <Stack>
            <h1>PurchaseOrders</h1>
            <Row>
                <Col />
                <Col>
                    <Button variant="primary" onClick={handleCreate}>Create Purchase Order</Button>{' '}
                </Col>
            </Row>
        </Stack>

            <PurchaseOrderTable orders ={response} callBackDelete={handleCallBackDelete}/>
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Purchase Order</Modal.Title>
                </Modal.Header>
                <Modal.Body>

                    <Form>
                        <Form.Group className="mb-3" controlId="name">
                            <Form.Label>Name</Form.Label>
                            <Form.Control type="text" placeholder="Enter Order Name" onChange={handleChange} />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="orderDate">
                            <Form.Label>Order Date</Form.Label>

                            <Form.Control type="date" placeholder="Enter Order Date" onChange={handleChange} />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="receivedDate">
                            <Form.Label>Received Date</Form.Label>
                            <Form.Control type="date" placeholder="Enter received date" onChange={handleChange} />
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
    );
};

export default PurchaseOrder;
