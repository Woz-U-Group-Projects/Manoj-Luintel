import {useEffect, useState} from "react";
import axios from "axios";

export const useSalesOrder = () => {
    const [response, setResponse] = useState();
    const [error, setError] = useState();
    const [loading, setLoading] = useState(false);
    const [show, setShow] = useState(false);
    const [salesOrder, setSalesOrder] = useState({
        id: null,
        name: '',
        orderDate: '',
        shippingDate: ''
    });

    const handleClose = () => setShow(false);
    const handleCreate = () => {
        setShow(true);
    };

    useEffect(async () => {
        await axios.get("http://localhost:9000/inv/sales-orders")
            .then(response => setResponse(response.data.data))
            .catch(error => setError(error));
        setLoading(false);
    }, [loading]);

    const handleSubmit = async () => {
        setLoading(true);
        await axios.post("http://localhost:9000/inv/sales-order", salesOrder)
            .then(response => console.log(response))
            .catch(error => setError(error))
            .finally(() => {
                setLoading(false);
                setShow(false);
            })
    };

    const handleChange = (e) => {
        const {id, value} = e.target;
        setSalesOrder(prevState => ({
            ...prevState,
            [id]: value
        }));
    };

    const handleCallBackDelete = async (order) => {
        setLoading(true);
        await axios.delete("http://localhost:9000/inv/sales-order", {params: {id: order.id}})
            .catch(error => setError(error))
            .finally(() => {
                setLoading(true)
            });
    };


    return {
        response, error, loading, show, handleClose, handleCreate, handleSubmit, handleCallBackDelete, handleChange
    };
}