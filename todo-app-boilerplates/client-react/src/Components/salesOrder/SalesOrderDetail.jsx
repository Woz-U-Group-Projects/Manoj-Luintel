import React from 'react';
import {useLocation} from "react-router-dom";
import {useSalesOrderDetails} from "../../hooks/SalesOrder/useSalesOrderDetails";
import {Button, Card, Col, Container, Form, Row} from "react-bootstrap";
import PurchaseOrderProductTable from "../purchaseOrder/PurchaseOrderProductTable";
import SalesOrderProductTable from "./SalesOrderProductTable";


const SalesOrderDetail = () => {

    const {loading,
        edit,
        order,
        handleOrderSave,
        handleOrderEdit,
        handleOrderEditCancel,
        handleChange,
        handleCompleteOrder,
        handleCancelOrder} = useSalesOrderDetails();

    return (
        <>
            <Container fluid>
                <h1>{`SalesOrder-${order.id}`}</h1>
                <Row>
                    {edit ?
                        <Col>
                            <Button variant="primary" onClick={handleOrderSave}>Save</Button>{''}
                            <Button variant="primary" onClick={handleOrderEditCancel}>Cancel</Button>{''}
                        </Col> :
                        <Col><Button variant="primary" onClick={handleOrderEdit}>Edit</Button>{''}</Col>

                    }
                    {order.status === "Created" &&
                        <Col >
                            <div style={{float: "right", justifyContent:"space-between"}}>
                                <Button variant="primary" onClick={handleCompleteOrder}>Complete Order</Button>{''}
                                <Button variant="danger" onClick={handleCancelOrder}>Cancel Order</Button>{''}
                            </div>
                        </Col>
                    }
                </Row>

                <Card>
                    <Card.Body>
                        <Container>
                            <Form>
                                <Row>
                                    <Form.Group className="mb-3" controlId="id">
                                        <Form.Control type="number" hidden={true} value={order.id}
                                                      onChange={handleChange}/>
                                    </Form.Group>

                                    <Col sm>
                                        <Form.Group className="mb-3" controlId="name">
                                            <Form.Label>Name</Form.Label>
                                            <Form.Control type="text" readOnly={!edit} value={order.name}
                                                          onChange={handleChange}/>
                                        </Form.Group>
                                    </Col>
                                    <Col sm>
                                        <Form.Group className="mb-3" controlId="status">
                                            <Form.Label>Status</Form.Label>
                                            <Form.Control type="text" readOnly={true} value={order.status}/>
                                        </Form.Group>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col sm>
                                        <Form.Group className="mb-3" controlId="orderDate">
                                            <Form.Label>Order Date</Form.Label>
                                            <Form.Control type="date" readOnly={!edit} value={order.orderDate}
                                                          onChange={handleChange}/>
                                        </Form.Group>
                                    </Col>
                                    <Col sm>
                                        <Form.Group className="mb-3" controlId="shippingDate">
                                            <Form.Label>Received Date</Form.Label>
                                            <Form.Control type="date" readOnly={!edit} value={order.shippingDate}
                                                          onChange={handleChange}/>
                                        </Form.Group>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col sm>
                                        <Form.Group className="mb-3" controlId="totalCost">
                                            <Form.Label>Total Cost</Form.Label>
                                            <Form.Control type="number" readOnly={true} value={order.totalCost}
                                                          onChange={handleChange}/>
                                        </Form.Group>
                                    </Col>
                                </Row>
                            </Form>
                        </Container>

                    </Card.Body>
                </Card>
                {order?.id !=='' && <SalesOrderProductTable salesOrderId ={order?.id}/>}
            </Container>
        </>
    );
};

export default SalesOrderDetail;
