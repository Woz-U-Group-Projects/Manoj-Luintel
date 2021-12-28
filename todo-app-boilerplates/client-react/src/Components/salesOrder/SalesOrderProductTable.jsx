import React from 'react';
import {Alert, Button, Container, Form, Modal, Table} from "react-bootstrap";
import CustomSpinner from "../CustomComponents/CustomSpinner";
import {usePurchaseOrderTable} from "../../hooks/PurchaseOrder/usePurchaseOrderTable";
import {useSalesOrderProductTable} from "../../hooks/SalesOrder/useSalesOrderProductTable";

const SalesOrderProductTable = (props) => {
    const {
        orderProducts,
        show,
        products,
        handleDelete,
        handleUpdate,
        handleClose,
        handleSubmit,
        handleChange,
        handleAddProduct,
        loading,
        message
    } = useSalesOrderProductTable(props)
    return (
        <>
            {message != undefined && <Alert variant={"danger"} dismissible>{message}</Alert>}
            <Container fluid>
                <Button variant="primary" onClick={() => handleAddProduct()}>Add Product</Button>{''}
                <Table responsive="sm">
                    <thead>
                    <tr>
                        <th>#Id</th>
                        <th>Name</th>
                        <th>Units</th>
                        <th>Cost</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    {orderProducts && orderProducts.map((orderProduct, i) => (
                        <tr key={orderProduct.id}>
                            <td>{i + 1}</td>
                            <td>{orderProduct.product.name}</td>
                            <td>{orderProduct.units}</td>
                            <td>{orderProduct.cost}</td>
                            <td>
                                <Button variant="info" onClick={() => handleUpdate(orderProduct)}>Update</Button>{' '}
                                <Button variant="danger" onClick={() => handleDelete(orderProduct)}>Delete</Button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </Table>


            </Container>

            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Add Product</Modal.Title>
                </Modal.Header>
                <Modal.Body>

                    <Form>
                        <Form.Group className="mb-3" controlId="id">
                            <Form.Control type="number" hidden={true} onChange={handleChange}/>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="salesOrderId">
                            <Form.Control type="number" hidden={true} value={props.salesOrderId}
                                          onChange={handleChange}/>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="productId">
                            <Form.Label>Select product</Form.Label>
                            <Form.Control as="select" onChange={(e)=>handleChange(e)}>
                                <option disabled={true} selected={true}>Click to select product</option>
                                {products.map((p, i) => ((
                                    <option key={p?.id} value={Number(p?.id)}>{p?.name}</option>
                                )))}
                            </Form.Control>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="units">
                            <Form.Label>Units</Form.Label>
                            <Form.Control type="number" placeholder="Enter units" onChange={handleChange}/>
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="cost">
                            <Form.Label>Cost</Form.Label>
                            <Form.Control type="number" placeholder="Enter cost" onChange={handleChange}/>
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

        </>
    );
};

export default SalesOrderProductTable;
