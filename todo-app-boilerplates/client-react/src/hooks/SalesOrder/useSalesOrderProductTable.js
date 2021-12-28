import {useEffect, useState} from "react";
import axios from "axios";

export const useSalesOrderProductTable = (props) => {
    const [orderProducts, setOrderProducts] = useState([]);
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState();
    const [error, setError] = useState();
    const [show, setShow] = useState(false);
    const [products, setProducts] = useState([]);
    const [salesOrderProduct, setSalesOrderProduct] = useState({
        id: 0,
        salesOrderId: props.salesOrderId,
        productId: 0,
        units: 0,
        cost: 0
    });

    useEffect(async () => {
        console.log(props.purchaseOrderId)
        await axios.get("http://localhost:9000/inv/sales-order-products",
            {params: {purchaseOrderId: props?.salesOrderId}})
            .then(response => setOrderProducts(response.data.data))
            .catch((error) => {
                const resMessage =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();
                setMessage(resMessage);

            });
        setLoading(false);

    }, [loading]);
    console.log(message)

    const handleClose = () => setShow(false);

    const handleChange = (e) => {
        console.log(e.target)
        e.preventDefault();
        const {id, value} = e.target;
        setSalesOrderProduct(prevState => ({
            ...prevState,
            [id]: value
        }));
    }

    const getProducts = async () => {
        setLoading(true);
        await axios.get("http://localhost:9000/inv/products")
            .then(response => setProducts(response.data.data))
            .catch(error => setError(error));
        setLoading(false);
    };

    const handleSubmit = async () => {
        setLoading(true);
        await axios.post("http://localhost:9000/inv/sales-order-product", salesOrderProduct)
            .catch(error => setError(error))
            .finally(() => {
                setLoading(false)
                setShow(false)
            });

    };

    const handleAddProduct = async () => {
        setShow(true);
        await getProducts();
    };

    const handleUpdate = async (orderProduct) => {
        await axios.put("http://localhost:9000/inv/sales-order-product", salesOrderProduct)
            .catch(error => setError(error))
            .finally(() => {
                setLoading(false)
                setShow(false)
            });
    };
    const handleDelete = async (orderProduct) => {
        setLoading(true)
        await axios.delete("http://localhost:9000/inv/sales-order-product",
            {params: {id: orderProduct.id}})
            .catch(error => setError(error))
            .finally(() => {
                setLoading(false)
            });
    };


    return {
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
        message,
    };
}