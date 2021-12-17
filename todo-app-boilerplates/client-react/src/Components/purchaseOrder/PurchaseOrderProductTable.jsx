import React, {useEffect, useState} from 'react';
import {Button, Container, Dropdown, Form, Modal, Table} from "react-bootstrap";
import {Link} from "react-router-dom";
import axios from "axios";
import CustomSpinner from "../CustomComponents/CustomSpinner";

const PurchaseOrderProductTable = (props) => {
    const[orderProducts,setOrderProducts] = useState([]);
    const[loading,setLoading] = useState(false);
    const[show,setShow] = useState(false);
    const[products ,setProducts] = useState([]);
    const[purchaseOrderProduct,setPurchaseOrderProduct] = useState({
        id: 0,
        purchaseOrderId:props.purchaseOrderId,
        productId :0,
        units : 0
    });

    useEffect(async () => {
       await axios.get("http://localhost:9000/inv/purchase-order-products",
            {params: {purchaseOrderId: props.purchaseOrderId}})
            .then(response => setOrderProducts(response.data.data))
            .catch(error => console.log(error));
        setLoading(false);

    },[loading]);

    const handleClose = () => setShow(false);

    const handleChange= (e) =>{
        console.log(e.target)
        const{id,value} = e.target;
        setPurchaseOrderProduct(prevState => ({
            ...prevState,
            [id]: value
        }));
    }

    const getProducts= async ()=>{
        await axios.get("http://localhost:9000/inv/products")
            .then(response => setProducts(response.data.data))
            .catch(error => console.log(error));
    };

    const handleSubmit = async ()=>{
        console.log(purchaseOrderProduct)
        await axios.post("http://localhost:9000/inv/purchase-order-product",purchaseOrderProduct)
            .catch(error => console.log(error))
            .finally(() => {
                setLoading(false)
                setShow(false)});

    };

    const handleAddProduct = async ()=>{
        setShow(true);
        await getProducts();
    };

   const handleUpdate = async (orderProduct) => {
       await axios.put("http://localhost:9000/inv/purchase-order-product", purchaseOrderProduct)
           .catch(error => console.log(error))
           .finally(() => {
               setLoading(false)
               setShow(false)
           });
   };
   const handleDelete = async (orderProduct) => {
       setLoading(true)
       await axios.delete("http://localhost:9000/inv/purchase-order-product",
           {params:{id:orderProduct.id}})
           .catch(error => console.log(error))
           .finally(() => {
               setLoading(false)
           });
   };


    return (
        <>
            <Container fluid>
                <Button variant="primary" onClick={handleAddProduct}>Add Product</Button>{''}
                <Table responsive="sm">
                    <thead>
                    <tr>
                        <th>#Id</th>
                        <th>Name</th>
                        <th>Units</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    { orderProducts && orderProducts.map((orderProduct, i) => (
                        <tr key={orderProduct.id}>
                            <td>{i+1}</td>
                            <td>{orderProduct.product.name}</td>
                            <td>{orderProduct.units}</td>
                            <td>
                                <Button variant="info" onClick={() =>handleUpdate(orderProduct)}>Update</Button>{' '}
                                <Button variant="danger" onClick={() =>handleDelete(orderProduct)}>Delete</Button>
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
                        <Form.Group className="mb-3" controlId="purchaseOrderId">
                            <Form.Control type="number" hidden={true} value={props.purchaseOrderId} onChange={handleChange}/>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="productId">
                            <Form.Label>Select product</Form.Label>
                            <Form.Control as="select" onChange={handleChange}>
                                {products.map((p,i)=>((
                                    <option key={p.id} value={Number(p.id)}>{p.name}</option>
                                )))}
                            </Form.Control>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="units">
                            <Form.Label>Units</Form.Label>
                            <Form.Control type="number" placeholder="Enter units" onChange={handleChange} />
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

export default PurchaseOrderProductTable;
