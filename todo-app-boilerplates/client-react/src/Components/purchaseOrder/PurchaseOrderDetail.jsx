import React, {useEffect, useState} from 'react';
import {useLocation} from "react-router-dom";
import {Button, Card, Col, Container, Form, Row, Stack} from "react-bootstrap";
import axios from "axios";
import PurchaseOrderProductTable from "./PurchaseOrderProductTable";

function useQuery() {
    const {search} = useLocation();

    return React.useMemo(() => new URLSearchParams(search), [search]);
}

const PurchaseOrderDetail = () => {
    const query = useQuery();
    const id = query.get("id");
    const [loading, setLoading] = useState(false);
    const [edit, setEdit] = useState(false);
    const [order, setOrder] = useState({
        id: '',
        name: '',
        orderDate: '',
        receivedDate: ''
    });


    useEffect(async () => {
        await axios.get("http://localhost:9000/inv/purchase-orders",
            {params: {id: id}})
            .then(response => setOrder(response.data.data))
            .catch(error => console.log(error));
        setLoading(false);
    }, [loading]);

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
    return (
        <>
            <Container fluid>
                <h1>{`PurchaseOrder-${order.id}`}</h1>
                {edit ?
                    <div>
                        <Button variant="primary" onClick={handleOrderSave}>Save</Button>{''}
                        <Button variant="primary" onClick={handleOrderEditCancel}>Cancel</Button>{''}
                    </div> :
                    <div><Button variant="primary" onClick={handleOrderEdit}>Edit</Button>{''}</div>

                }

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
                            </Form>
                        </Container>

                    </Card.Body>
                </Card>
                {order.id !=='' && <PurchaseOrderProductTable purchaseOrderId ={order.id}/>}
            </Container>
        </>
    );
};

export default PurchaseOrderDetail;
