import React from 'react';
import {Button, Col, Container, Form, Modal, Row, Stack} from "react-bootstrap";
import PurchaseOrderTable from "../purchaseOrder/PurchaseOrderTable";
import CustomSpinner from "../CustomComponents/CustomSpinner";
import {useSalesOrder} from "../../hooks/SalesOrder/useSalesOrder";
import SalesOrderTable from "./SalesOrderTable";

const SalesOrder = () => {
    const {
        response,
        error,
        loading,
        show,
        handleClose,
        handleCreate,
        handleSubmit,
        handleCallBackDelete,
        handleChange
    } = useSalesOrder();
    return (
        <Container fluid>
            <Stack>
                <h1>SalesOrders</h1>
                <Row>
                    <Col/>
                    <Col>
                        <Button variant="primary" onClick={handleCreate}>Create Sales Order</Button>{' '}
                    </Col>
                </Row>
            </Stack>

            <SalesOrderTable orders={response} callBackDelete={handleCallBackDelete}/>
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Sales Order</Modal.Title>
                </Modal.Header>
                <Modal.Body>

                    <Form>
                        <Form.Group className="mb-3" controlId="name">
                            <Form.Label>Name</Form.Label>
                            <Form.Control type="text" placeholder="Enter Order Name" onChange={handleChange}/>
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="orderDate">
                            <Form.Label>Order Date</Form.Label>

                            <Form.Control type="date" placeholder="Enter Order Date" onChange={handleChange}/>
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="shippingDate">
                            <Form.Label>Shipping Date</Form.Label>
                            <Form.Control type="date" placeholder="Enter received date" onChange={handleChange}/>
                        </Form.Group>

                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={handleSubmit}>
                        {loading ? <CustomSpinner/> : "Submit"}
                    </Button>
                </Modal.Footer>
            </Modal>

        </Container>
    );
};

export default SalesOrder;
