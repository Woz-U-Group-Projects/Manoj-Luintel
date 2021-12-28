import React, {useEffect, useState} from 'react';
import {useLocation} from "react-router-dom";
import {Button, Card, Col, Container, Form, Row, Stack} from "react-bootstrap";
import axios from "axios";
import PurchaseOrderProductTable from "./PurchaseOrderProductTable";
import {usePurchaseOrderTable} from "../../hooks/PurchaseOrder/usePurchaseOrderTable";

function useQuery() {
    const {search} = useLocation();

    return React.useMemo(() => new URLSearchParams(search), [search]);
}

const PurchaseOrderDetail = () => {
    const query = useQuery();
    const id = query.get("id");
    const [loading, setLoading] = useState(false);
    const [edit, setEdit] = useState(false);
    const {loading:purchaseTableLoading}=usePurchaseOrderTable(id)
    const [order, setOrder] = useState({
        id: '',
        name: '',
        orderDate: '',
        receivedDate: '',
        totalCost: 0
    });


    useEffect(async () => {
        await axios.get("http://localhost:9000/inv/purchase-orders",
            {params: {id: id}})
            .then(response => setOrder(response.data.data))
            .catch(error => console.log(error));
        setLoading(false);
    }, [loading,purchaseTableLoading]);

    const handleOrderEdit = () => {
        setEdit(true);
    };
    const handleOrderSave =async () => {
        await axios.put("http://localhost:9000/inv/purchase-order",order)
            .catch(error => console.log(error))
            .finally(() => {
                setLoading(false)
                setEdit(false)});
    };

    const handleOrderEditCancel = () => {
        setEdit(false);
        setLoading(true);
    };

    const handleChange = (e) => {
        const {id, value} = e.target;
        setOrder(prevState => ({
            ...prevState,
            [id]: value
        }));
    };

    const handleCompleteOrder = async () => {
        setLoading(true);
        await axios.put(`http://localhost:9000/inv/purchase-order-complete?id=${parseInt(id)}`)
            .catch(error => console.log(error))
            .finally(() => {
                setLoading(false)
                setEdit(false)
            });

    }
    const handleCancelOrder = async () => {
        setLoading(true);
        await axios.put(`http://localhost:9000/inv/purchase-order-cancel?id=${parseInt(id)}`)
            .catch(error => console.log(error))
            .finally(() => {
                setLoading(false)
                setEdit(false)
            });

    };
    return (
        <>
            <Container fluid>
                <h1>{`PurchaseOrder-${order.id}`}</h1>
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
                                        <Form.Group className="mb-3" controlId="receivedDate">
                                            <Form.Label>Received Date</Form.Label>
                                            <Form.Control type="date" readOnly={!edit} value={order.receivedDate}
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
                {order?.id !=='' && <PurchaseOrderProductTable purchaseOrderId ={order?.id}/>}
            </Container>
        </>
    );
};

export default PurchaseOrderDetail;
