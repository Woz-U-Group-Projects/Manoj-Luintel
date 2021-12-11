import React, {useState} from 'react';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import BootstrapTable from 'react-bootstrap-table-next';
import {Button, Form, Modal, Table} from "react-bootstrap";
import axios from "axios";
import CustomSpinner from "./ui/CustomSpinner";

const MyComponent = (props) => {
    const [showUpdate, setShowUpdate] = useState(false);
    const [loading,setLoading] = useState(false);
    const[product,setProduct] = useState({id: 0,
        name :'',
        code :"",
        costPrice : 0,
        sellingPrice :0,
        description :"",
        unitsInStock :0,
        lowStockThreshold: 0});


    const handleUpdateClose = () => setShowUpdate(false);


    const handleSubmit=async () => {
        setLoading(true);
        await axios.put("http://localhost:9000/inv/product", product)
            .catch(error => console.log(error))
            .finally(() => {
                setShowUpdate(false)
                setLoading(false);
            });
        product && props.handleUpdateCallBack();
    };

    const handleChange= (e) =>{
        const{id,value} = e.target;
        setProduct(prevState => ({
            ...prevState,
            [id]: value
        }));
    };


    const handleDelete =(product)=>{
        product && props.callBackDelete(product);
    };

    const handleUpdate =(productArg)=>{
        setProduct(productArg);
        setShowUpdate(true)
    };
    if(!props.products ) return null;


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
                    <tr key={product.id}>
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



            <Modal show={showUpdate} onHide={handleUpdateClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Update Product</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group className="mb-3" controlId="id">
                            <Form.Control type="number"  value={product.id} hidden={true} onChange={handleChange}/>
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="name">
                            <Form.Label>Product Name</Form.Label>
                            <Form.Control type="text"  value={product.name} placeholder="Enter Product Name" onChange={handleChange} />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="code">
                            <Form.Label>Product Code</Form.Label>
                            <Form.Control  type="text" value={product.code} placeholder="Enter Code" onChange={handleChange} />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="costPrice">
                            <Form.Label>Cost Price</Form.Label>
                            <Form.Control type="number" value={product.costPrice} placeholder="Enter Cost price" onChange={handleChange} />
                        </Form.Group>


                        <Form.Group className="mb-3" controlId="sellingPrice">
                            <Form.Label>Selling Price</Form.Label>
                            <Form.Control type="number" value={product.sellingPrice} placeholder="Enter Selling price" onChange={handleChange} />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="unitsInStock">
                            <Form.Label>Stock</Form.Label>
                            <Form.Control type="number" value={product.unitsInStock} placeholder="Enter current stock" onChange={handleChange} />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="lowStockThreshold">
                            <Form.Label>Low stock Threshold</Form.Label>
                            <Form.Control type="number" value={product.lowStockThreshold} placeholder="Enter low stock threshold" onChange={handleChange} />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="description">
                            <Form.Label>Description</Form.Label>
                            <Form.Control type="text" value={product.description} placeholder="Enter Description" onChange={handleChange} />
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleUpdateClose}>
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

export default MyComponent;
